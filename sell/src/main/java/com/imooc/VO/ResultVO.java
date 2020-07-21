package com.imooc.VO;

import lombok.Data;

/**
 * the returned most outer layer object corresponding to a http request
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-14 23:05
 **/
@Data
public class ResultVO<T> {
    // wrong code
    private Integer code;

    //hint
    private String msg;

    //detail information
    private T data;
}
