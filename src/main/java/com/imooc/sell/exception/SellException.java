package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

import lombok.Getter;

/**
 * 异常信息
 */
@Getter
public class SellException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
