package com.imooc.service.impl;

import com.imooc.dtoDataTransferObject.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-22 22:13
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkUserOpenID(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkUserOpenID(openid, orderId);
        if(orderDTO == null){
            log.error("[cancel order] can not find the order,  orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkUserOpenID(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }

        //determine the order's openid is the same as the parameter openid
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[search order] openid is different, openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.WRONG_USER_OPENID);
        }

        return orderDTO;
    }
}
