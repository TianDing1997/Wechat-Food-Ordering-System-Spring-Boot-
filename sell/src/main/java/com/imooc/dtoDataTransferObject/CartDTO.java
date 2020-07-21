package com.imooc.dtoDataTransferObject;

import lombok.Getter;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-18 22:24
 * @ Cart: contains product id and product quantity in the cart
 **/
@Getter
public class CartDTO {
    /** product id */
    private String productId;

    /** product quantity */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
