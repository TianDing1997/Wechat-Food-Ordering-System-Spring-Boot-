package com.imooc.dtoDataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.utils.EnumUtil;
import com.imooc.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-17 22:27
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /** order id */
    private String orderId;

    /** buyer name */
    private String buyerName;

    /** buyer phone */
    private String buyerPhone;

    /** buyer address */
    private String buyerAddress;

    /** buyer open id */
    private String buyerOpenid;

    /** order amount */
    private BigDecimal orderAmount;

    /** order status, default is new order */
    private Integer orderStatus;

    /** pay status, default is unpaid */
    private Integer payStatus;

    /** create time */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** update time */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /** order detail */
    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
