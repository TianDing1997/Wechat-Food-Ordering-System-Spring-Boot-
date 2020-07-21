package com.imooc.service;

import com.imooc.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-13 22:36
 **/
public interface CategoryService {
    ProductCategory findOne(Integer categroyId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

    ProductCategory save(ProductCategory productCategory);
}
