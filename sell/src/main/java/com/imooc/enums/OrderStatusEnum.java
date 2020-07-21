package com.imooc.enums;

import lombok.Getter;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-17 20:43
 **/
@Getter
public enum OrderStatusEnum {
    NEW(0, "new order"),
    FINISHED(1, "finished order"),
    CANCEL(2, "canceled")
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
