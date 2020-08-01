package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-27 23:21
 **/
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
