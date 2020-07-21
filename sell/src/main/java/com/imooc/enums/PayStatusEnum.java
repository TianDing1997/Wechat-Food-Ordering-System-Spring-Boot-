package com.imooc.enums;

import lombok.Getter;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-17 20:47
 **/
@Getter
public enum PayStatusEnum {
    WAIT(0, "unpaid"),
    SUCCESS(1, "paid");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
