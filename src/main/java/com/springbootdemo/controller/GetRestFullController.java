package com.springbootdemo.controller;

import com.springbootdemo.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(path = "/v2/{city_id}/{user_id}",method = RequestMethod.GET)
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


    /**
     * 通过@RequestParam注解规范参数的默认值和与前端参数的对应Key值
     * @param from
     * @param size
     * @return
     */
    public Object pageUserV2(@RequestParam(defaultValue = "0",name = "page") int from,int size){
        params.clear();
        params.put("from",from);
        params.put("size",size);
        return params;
    }


    /**
     * 最常用的方式: 使用bean对象pojo的方式传参
     *注意:1. 注意需要指定http头尾 content-type 为application/json
     *     2.使用Body(请求体)传输数据
     *     3.前端没传入的字段,默认赋值空
     * @param user
     * @return
     */
    @RequestMapping("/v1/save_user")
    public Object saveUser(@RequestBody User user){
        params.clear();
        params.put("user",user);
        return params;
    }


    /**
     * 获取http的头信息
     *  在需要获取前端发送来的Token的时候,可以使用该注解RequestHeader直接进行直接获取
     * @param accessToken
     * @param id
     * @return
     */
    @GetMapping("/v1/get_header")
    public Object getHeader(@RequestHeader("access_token") String accessToken,String id){
            params.clear();
            params.put("access_token",accessToken);
            params.put("id",id);
            return params;
    }

    /**
     * 通过Servlet的方式获取前端传入的参数,基本上不用
     * @param request
     * @return
     */
    @GetMapping("/V1/test_request")
    public Object testRequest(HttpServletRequest request){
        params.clear();
        String id = request.getParameter("id");
        params.put("id",id);
        return params;
    }





}
