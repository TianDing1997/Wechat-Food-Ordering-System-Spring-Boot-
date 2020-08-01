package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-20 22:41
 **/
@Data
public class OrderForm {

    //buyer name
    @NotEmpty(message = "must have name")
    private String name;

    //buyer phone number
    @NotEmpty(message = "must have phone number")
    private String phone;

    //buyer address
    @NotEmpty(message = "must have address")
    private String address;

    //buyer wechat open id
    @NotEmpty(message = "must have wechat open id")
    private String openid;

    //cart
    @NotEmpty(message = "cart can not be empty")
    private String items;


}
