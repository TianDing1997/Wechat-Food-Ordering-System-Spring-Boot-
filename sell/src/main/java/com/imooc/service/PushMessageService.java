package com.imooc.service;

import com.imooc.dtoDataTransferObject.OrderDTO;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-08-02 21:09
 * @ push message
 **/
public interface PushMessageService {

    /**
     * order status update message
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
