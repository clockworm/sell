package com.imooc.sell.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.exception.ResponseBankException;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.exception.SellerAuthorizeException;
import com.imooc.sell.util.ResultVOUtil;
import com.imooc.sell.vo.ResultVO;

@ControllerAdvice
public class AuthorizeExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /** 拦截登录异常 控制器*/
    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:".concat(projectUrlConfig.getSell()).concat("/sell/wechat/qrAuthorize").concat("?returnUrl=").concat(projectUrlConfig.getSell()).concat("/sell/user/login"));
    }


    /** 业务异常控制器*/
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO<?> handlerSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }



    /** 特殊业务异常控制器 返回HTTP 头消息状态为403*/
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public void  handlerSellException() {
    }
}
