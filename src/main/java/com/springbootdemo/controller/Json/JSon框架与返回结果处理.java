package com.springbootdemo.controller.Json;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *  	1、常用框架 阿里 fastjson,谷歌gson等
 *   		JavaBean序列化为Json，性能：Jackson > FastJson > Gson > Json-lib 同个结构
 *   		Jackson、FastJson、Gson类库各有优点，各有自己的专长
 *  		空间换时间，时间换空间
 */
@RestController
public class JSon框架与返回结果处理 {
    @GetMapping("/testjson")
    public Object testjson(){
        return new User测试实体类(null,"abc123","1000100",new Date());

    }

}
