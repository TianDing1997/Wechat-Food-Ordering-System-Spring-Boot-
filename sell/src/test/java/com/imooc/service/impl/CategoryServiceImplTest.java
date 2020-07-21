package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-13 22:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest{

    @Autowired
    private  CategoryServiceImpl categoryService;

    @Test
    public void testFindOne() throws Exception{
        ProductCategory productCategory = categoryService.findOne(3);
        Assert.assertEquals(new Integer(3), productCategory.getCategoryId());
    }

    @Test
    public void testFindAll() throws Exception{
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void testFindByCategoryTypeIn() throws  Exception{
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void testSave() throws  Exception{
        ProductCategory productCategory = new ProductCategory("男生专享",4);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotEquals(null, result);
    }
}