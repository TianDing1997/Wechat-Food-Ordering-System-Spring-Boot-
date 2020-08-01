package com.imooc.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-29 22:43
 **/
@Data
public class ProductForm {

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

    //category type
    private Integer categoryType;
}
