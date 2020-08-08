package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-14 20:47
 **/
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 6399186181668983148L;

    @Id
    private  String productId;

    // name
    private String productName;

    // price
    private BigDecimal productPrice;

    //stock
    private Integer productStock;

    //description
    private String productDescription;

    //icon
    private String productIcon;

    //status, 0 normal, 1 cancel
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    //category type
    private Integer categoryType;

    //create Time
    private Date createTime;

    //update Time
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }





}
