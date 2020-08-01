package com.imooc.service;

import com.imooc.dtoDataTransferObject.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-25 22:07
 **/
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);

}
