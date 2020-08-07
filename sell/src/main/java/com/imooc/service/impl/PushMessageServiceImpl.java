package com.imooc.service.impl;

import com.imooc.config.WechatAccountConfig;
import com.imooc.dtoDataTransferObject.OrderDTO;
import com.imooc.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-08-02 21:10
 **/
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    private static final String OPENID = "o1mgC6h9N_RdQuPiyZvUEtnu_RaI";

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
        //because we do not have the business official account, we set a default open id;
        //templateMessage.setToUser(orderDTO.getBuyerOpenid());
        templateMessage.setToUser(OPENID);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "Order Delivered."),
                new WxMpTemplateData("keyword1", "123456789"),
                new WxMpTemplateData("keyword2", "123456789"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword5", "$" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "Thanks for order")
        );
        templateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("[Wechat Template Message] push failed, {}", e.getMessage());
        }
    }
}
