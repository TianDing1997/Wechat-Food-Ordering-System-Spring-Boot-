package com.imooc.utils;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-27 18:25
 **/
public class MathUtil {

    private static final Double Money_Range = 0.01;
    /**
     * compare two amount is the same
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2){
        Double result = Math.abs(d1 - d2);
        if(result < Money_Range){
            return true;
        }
        else{
            return false;
        }
    }
}
