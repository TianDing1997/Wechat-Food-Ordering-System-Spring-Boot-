package com.imooc.service;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dtoDataTransferObject.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-17 22:19
 **/
public interface OrderService {

    /** create order */
    OrderDTO create(OrderDTO orderDTO);

    /** search one order */
    OrderDTO findOne(String orderId);

    /** search one buyer's all orders */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** cancel order */
    OrderDTO cancel(OrderDTO orderDTO);

    /** complete order*/
    OrderDTO finish(OrderDTO orderDTO);

    /** pay the order */
    OrderDTO paid(OrderDTO orderDTO);

    /** search all buyer's all orders */
    Page<OrderDTO> findList(Pageable pageable);

}
