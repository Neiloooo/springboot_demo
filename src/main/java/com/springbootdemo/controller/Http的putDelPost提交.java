package com.springbootdemo.controller;

import com.springbootdemo.controller.JpaLearn.complexJpa.Users;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//测试http协议的post,del,put请求
@RestController
public class Http的putDelPost提交 {

    private Map<String,Object> params = new HashMap<>();





    /**
     * 测试PostMapping的提交方式(一般提交,新增)
     * @param id
     * @param pwd
     * @return
     */
    @PostMapping("/v1/login")
    public Object login(String id,String pwd){
        params.clear();
        params.put("id",id);
        params.put("pwd",pwd);
        return params;
    }

    /**
     * 一般用于更新
     * @param id
     * @return
     */
    @PutMapping("/V1/PUT")
    public Object put(String id){
        params.clear();
        params.put("id",id);
        return params;
    }

    /**
     * 一般用于删除
     * @param id
     * @return
     */
    @DeleteMapping("/v1/del")
    public Object del(String id){
        params.clear();
        params.put("id",id);
        return params;
    }
}
