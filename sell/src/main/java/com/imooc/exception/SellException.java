package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-18 21:53
 **/
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }
}
