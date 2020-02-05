package com.springbootdemo.controller.配置文件的注入;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:resource.properties") //配置文件的全名
public class Springboot配置文件的加载 {

    @Value("${web.file.path}")  //与配置文件中的key值相同,中文也没问题
    private String filepath;

    @GetMapping("v1/getPropertySource")
    public String  getPropertySource(){
        System.out.println(filepath);
        return filepath;
    }

    @Autowired
    private 实体类映射配置文件 javaBean;
    @GetMapping("/v1/test_javabean_perties")
    public Object testJavaBeanPerties(){
        return javaBean;

    }
}
