package com.springbootdemo.controller.异常测试;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@ControllerAdvice
public class 异常捕获类 {
    //捕获全局异常,处理所有不可知的异常
    //当抛出异常,会被这个东西捕获,处理后再返回给前端,AOP
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    Object handleException(Exception e, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",100);
        map.put("msg",e.getMessage());
        map.put("url",request.getRequestURI());
        return map;
    }


    /**
     * 当抛出自定义异常的时候会走这里进行返回,ExceptionHandler类匹配自定义的异常类
     * @param e
     * @return
     */
    @ExceptionHandler(value = 自定义异常.class)
    public Object handleMyException( 自定义异常 e,HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",e.getCode());
        map.put("msg",e.getMessage());
        map.put("url",request.getRequestURI());
        return map;
    }
}
