package com.imooc.dtoDataTransferObject;

import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
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
    private Date createTime;

    /** update time */
    private Date updateTime;

    /** order detail */
    List<OrderDetail> orderDetailList;
}
