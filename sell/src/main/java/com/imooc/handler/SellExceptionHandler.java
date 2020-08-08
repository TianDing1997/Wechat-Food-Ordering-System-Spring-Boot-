package com.imooc.handler;

import com.imooc.VO.ResultVO;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-08-01 21:31
 **/

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //catch login exception
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handleAuthorizeException(){
        //because we do not have an official account, we can only use this url to test:
        return new ModelAndView("redirect:".concat(projectUrlConfig.getAuthorize()));
//        return new ModelAndView("redirect:"
//                .concat(projectUrlConfig.getWechatMpAuthorize())
//                .concat("/sell/wechat/qrAuthorize")
//                .concat("?returnUrl=")
//                .concat(projectUrlConfig.getSell())
//                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
