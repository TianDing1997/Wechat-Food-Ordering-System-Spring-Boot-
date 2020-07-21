package com.imooc.enums;

import lombok.Getter;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-18 21:54
 **/
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "product not exist"),

    PRODUCT_STOCK_ERROR(11, "stock not enough"),

    ORDER_NOT_EXIST(12, "order not exist"),

    ORDERDETAIL_NOT_EXIST(13, "order detail not exist"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
