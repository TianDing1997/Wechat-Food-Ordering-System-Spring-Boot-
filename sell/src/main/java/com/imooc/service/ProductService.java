package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dtoDataTransferObject.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-14 21:17
 **/
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * find the product on sale
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //stock increase
    void increaseStock(List<CartDTO> cartDTOList);

    //stock decrease
    void decreaseStock(List<CartDTO> cartDTOList);
}
