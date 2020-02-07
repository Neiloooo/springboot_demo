package com.springbootdemo.controller.异常测试;

import com.springbootdemo.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常测试类
 */
@RestController
public class ExceptionController {

    @RequestMapping(value = "/api/v1/test_ext")
    public Object index(){
        return new User(11,"sfsds");
    }
}
