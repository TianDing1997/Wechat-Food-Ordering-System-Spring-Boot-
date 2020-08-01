package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-30 20:22
 * @Seller
 **/
public interface SellerService {

    /**
     * search seller infor by openid
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
