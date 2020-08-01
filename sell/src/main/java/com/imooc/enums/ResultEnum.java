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
    SUCCESS(0, "Operation Success"),

    PARAM_ERROR(1, "wrong parameters"),

    PRODUCT_NOT_EXIST(10, "product not exist"),

    PRODUCT_STOCK_ERROR(11, "stock not enough"),

    ORDER_NOT_EXIST(12, "order not exist"),

    ORDERDETAIL_NOT_EXIST(13, "order detail not exist"),

    ORDER_STATUS_ERROR(14, "wrong order status"),

    ORDER_UPDATE_FAIL(15, "fail to update the order status"),

    ORDER_DETAIL_EMPTY(16, "order detail is empty"),

    PAYMENT_STATUS_ERROR(17, "wrong payment status"),

    CART_IS_EMPTY(18, "cart is empty"),

    WRONG_USER_OPENID(19, "wrong user openid"),

    WX_MP_ERROR(20, "wechat mp account error"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"Wechat Payment Asynchronous Certification Fail"),

    ORDER_CANCEL_SUCCESS(22, "Order Cancel Success"),

    ORDER_COMPLETE_SUCCESS(23, "Order Complete Success"),

    PRODUCT_STATUS_ERROR(24, "product status error"),

    LOGIN_FAIL(25, "login fail, login information wrong"),


    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
