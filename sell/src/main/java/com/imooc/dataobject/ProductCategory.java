package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @类目
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-06-24 23:04
 * product_category
 **/
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    //category id
    @Id
    @GeneratedValue
    private Integer categoryId;

    //category name
    private String categoryName;

    //category type
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
