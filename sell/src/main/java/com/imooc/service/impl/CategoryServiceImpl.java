package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.repository.ProductCategoryRepository;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-13 22:38
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categroyId) {
        return repository.findOne(categroyId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
        return repository.findByCategoryTypeIn(categoryType);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
