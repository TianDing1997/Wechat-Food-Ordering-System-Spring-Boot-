package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-14 20:47
 **/
@Entity
@Data
public class ProductInfo {

    @Id
    private  String productId;

    // name
    private String productName;

    // price
    private BigDecimal productPrice;

    //stock
    private Integer productStock;

    //description
    private String productDescription;

    //icon
    private String productIcon;

    //status, 0 normal, 1 cancel
    private Integer productStatus;

    //category type
    private Integer categoryType;






}
