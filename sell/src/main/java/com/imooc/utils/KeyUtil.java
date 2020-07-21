package com.imooc.utils;

import java.util.Random;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-18 22:07
 * @Create random key
 **/
public class KeyUtil {

    /**
     * create unique primary key;
     * format: date + random number;
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + number + "";
    }
}
