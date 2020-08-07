package com.imooc.service.impl;

import com.imooc.dtoDataTransferObject.OrderDTO;
import com.imooc.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-08-02 21:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1595725813358");
        pushMessageService.orderStatus(orderDTO);
    }
}