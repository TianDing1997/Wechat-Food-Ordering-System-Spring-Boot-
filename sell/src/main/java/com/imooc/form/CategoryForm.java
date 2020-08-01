package com.imooc.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-30 17:46
 **/
@Data
public class CategoryForm {
    //category id
    private Integer categoryId;

    //category name
    private String categoryName;

    //category type
    private Integer categoryType;
}
