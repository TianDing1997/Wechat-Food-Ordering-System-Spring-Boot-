package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dtoDataTransferObject.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import com.imooc.service.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-14 21:27
 **/
@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {
    private static final int TIMEOUT = 20 * 1000; // redis lock time out 20 seconds

    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private RedisLock redisLock;

    @Override
    @Cacheable(key = "123") //can not remove the key value, if do not fill key, key will be the pruductId in this case
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //add lock
            long time = System.currentTimeMillis() + TIMEOUT;
            if(!redisLock.lock(cartDTO.getProductId(), String.valueOf(time))) {
                throw new SellException(ResultEnum.REDIS_LOCK);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);

            //Redis Unlock
            redisLock.unlock(cartDTO.getProductId(), String.valueOf(time));
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo  productInfo = repository.findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //update
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);

    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo  productInfo = repository.findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //update
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
