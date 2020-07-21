package com.imooc.dataobject;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-17 20:34
 **/
@Entity
@Data
//let the time update automatically
@DynamicUpdate
public class OrderMaster {

    /** order id */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** pay status, default is unpaid */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** create time */
    private Date createTime;

    /** update time */
    private Date updateTime;
}
