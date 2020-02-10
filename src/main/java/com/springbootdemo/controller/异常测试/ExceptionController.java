package com.springbootdemo.controller.异常测试;

import com.springbootdemo.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常测试类
 */
@Api(tags = "异常处理类")
@RestController
public class ExceptionController {

    @ApiOperation(value = "全局异常处理的测试类", notes = "AOP方式定义全局异常处理类")
    @RequestMapping(value = "/api/v1/test_ext")
    public Object index(){
        int i =1/0 ; //模拟异常
         return new User(11,"sfsds");
    }


    @ApiOperation(value = "自定义异常测试接口", notes = "测试自定义异常")
    @RequestMapping(value = "/api/v1/myext")
    public Object myexc(){

        throw new 自定义异常("468","抛出自定义异常");

    }

}
