package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-17 20:51
 **/
@Entity
@Data
public class OrderDetail {

    /** detail id */
    @Id
    private String detailId;

    /** order id */
    private String orderId;

    /** product id */
    private String productId;

    /** product name */
    private String productName;

    /** product price */
    private BigDecimal productPrice;

    /** product quantity */
    private Integer productQuantity;

    /** product icon */
    private String productIcon;

//    /** create time */
//    private Date createTime;
//
//    /** update time */
//    private Date updateTime;

}
