package com.imooc.service.impl;

import com.imooc.dtoDataTransferObject.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.utils.JsonUtil;
import com.imooc.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-25 22:07
 **/
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "WeChat Order";
    @Autowired
    BestPayServiceImpl bestPayService;

    @Autowired
    OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[WeChat Pay] send payment request, request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[WeChat Pay] send payment request, response={}", JsonUtil.toJson(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        /**
         * WeChat asynchronous tip:
         * 1.certificate sign
         * 2.payment status
         * 3. payment amount
         * 4. payment user
         */
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[WeChat Pay] asynchronous notification, payResponse={}", payResponse);

        //search order
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        //determine whether the order exists
        if(orderDTO == null) {
            log.error("[WeChat Pay] asynchronous notification, order not exists, orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //determine amount is the same(0.10 and 0.1 is the same)
        if(!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())){
            log.error("[WeChat Pay] asynchronous notification， " +
                    "payment amount is not the same, orderId={}, WeChat Notification Amount={}, System Amount={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }


        //change order payment status
        orderService.paid(orderDTO);
        return payResponse;
    }


    //refund
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[WeChat Refund] request={}", JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[WeChat Refund] response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
