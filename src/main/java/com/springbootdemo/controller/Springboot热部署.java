package com.springbootdemo.controller;

import com.springbootdemo.controller.Json.User测试实体类;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 加入dev-tools依赖后,即可实现,写代码的时候不用,鸡肋,每次修改一点,就自动重启,消耗性能
 * 但是在修改配置文件后,自动进行重启的功能还是很好的,会有很多应用场景可能会用到
 */
@RestController
public class Springboot热部署 {
    @GetMapping(value = "/v1/test_dev")
    public Object testdev(){
        System.out.println("你好");
        System.out.println("你好");
        System.out.println("你好");
        return new User测试实体类(null,"abc123","1000100",new Date());

    }
}
