package com.imooc.service.impl;

import com.imooc.converter.OrderMaster2OrderDTOConverter;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dtoDataTransferObject.CartDTO;
import com.imooc.dtoDataTransferObject.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.*;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-17 22:32
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Autowired
    private PayService payService;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;

    @Override
    //add transactional because if there is an error, the data will roll back
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
        //1. search product(amount, price)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. compute total price
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //save the detail in the database
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        //3. save the order master into the database (order master)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4. deduct the stock
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);


        //push websocket message
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailsList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailsList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailsList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        //check the order status
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[cancel order] wrong order status, orderId = {}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //change the order status to cancel
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[cancel order] update fail, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //restore the stock
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[cancel order] do not have order detail, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //if already pay, pay back
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //check the order status
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[finish order] wrong order status, orderId={}, orderStatus={}",orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //change the order status
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if(updateResult == null){
            log.error("[finish order] update fail, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //push wechat template message
        pushMessageService.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //check the order status
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[paid success] wrong order status, orderId={}, orderStatus={}",orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //check the payment status
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[paid success] wrong payment status, orderDTO={}", orderDTO);
            throw  new SellException(ResultEnum.PAYMENT_STATUS_ERROR);
        }

        //change the order status
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if(updateResult == null){
            log.error("[paid success] update fail, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());


    }
}
