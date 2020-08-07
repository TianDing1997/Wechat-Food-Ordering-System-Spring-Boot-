package com.imooc.dataobject.mapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.imooc.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-08-04 20:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "Lian Favorite");
        map.put("category_type", 101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("Lian Favorite");
        productCategory.setCategoryType(102);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findBycategoryType() {
        ProductCategory result = mapper.findBycategoryType(102);
        Assert.assertNotNull(result);
    }

    @Test
    public void findBycategoryName() {
        List<ProductCategory> result = mapper.findBycategoryName("Lian Favorite");
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("Hou Favorite", 102);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("Lian Favorite");
        productCategory.setCategoryType(102);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(102);
        Assert.assertEquals(1, result);
    }
}