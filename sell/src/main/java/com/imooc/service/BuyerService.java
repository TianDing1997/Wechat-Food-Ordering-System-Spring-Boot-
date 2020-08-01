package com.imooc.service;

import com.imooc.dtoDataTransferObject.OrderDTO;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-22 22:11
 *
 * buyer
 **/
public interface BuyerService {

    //check one order
    OrderDTO findOrderOne(String openid, String orderId);


    //cancel order
    OrderDTO cancelOrder(String openid, String orderId);
}
