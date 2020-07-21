package com.imooc.enums;

import lombok.Getter;

/**
 * 商品状态
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-14 21:30
 **/
@Getter
public enum ProductStatusEnum {
    UP(0, "on sale"),
    DOWN(1, "off sale")
    ;

    private Integer code;
    private String message;
    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
