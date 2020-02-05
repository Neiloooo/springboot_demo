package com.springbootdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//http协议符合restful风格接口的get请求
@RestController
public class GetRestFullController {


    private Map<String,Object> params=new HashMap<>();

    /**
     * 从url路径中获取参数,放入map中返回,无状态
     * @param cityId
     * @param userId
     * @return params
     */
    @RequestMapping(path = "/{city_id}/{user_id}",method = RequestMethod.GET)
    public Object findUser(@PathVariable("city_id") String cityId,
                           @PathVariable("user_id") String userId){
        params.clear();
        params.put("cityId",cityId);
        params.put("userId",userId);
        return params;
    }

    /**
     * 简化版的GetMapping注解,其中page_user1是第一个接口,url的参数直接拼接在其后面
     *例如:
     *      localhost:8080/page_user1?from=0&size=10
     *      两个参数拼接在了page_user1的后面,且key值与我们的变量名相同
     * @param from
     * @param size
     * @return
     */
    @GetMapping(value = "/v1/page_user1")
    public Object pageUser(int from, int size){
        params.clear();
        params.put("from",from);
        params.put("size",size);
        return params;
    }








}
