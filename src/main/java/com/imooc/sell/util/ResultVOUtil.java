package com.imooc.sell.util;

import com.imooc.sell.VO.ResultVO;

/**
 * 前端交互
 */
public class ResultVOUtil {

    //成功
    public static ResultVO<?> success(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO<?> success(){
        return  success(null);
    }

    //错误
    public  static  ResultVO<?> error(Integer code,String message){
        ResultVO<?> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(null);
        return resultVO;
    }

    //失敗
    public static ResultVO<?> fail(String message){
        ResultVO<?> resultVO = new ResultVO<>();
        resultVO.setCode(1);
        resultVO.setMessage(message);
        resultVO.setData(null);
        return resultVO;
    }
}
