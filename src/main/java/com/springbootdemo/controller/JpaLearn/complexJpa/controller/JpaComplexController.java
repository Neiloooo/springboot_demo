package com.springbootdemo.controller.JpaLearn.complexJpa.controller;


import com.springbootdemo.controller.JpaLearn.complexJpa.Users;
import com.springbootdemo.controller.JpaLearn.complexJpa.UsersDaoRepository;
import com.springbootdemo.controller.JpaLearn.complexJpa.service.UserServiceImpl;
import com.springbootdemo.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Api(tags = "JPA的单表增删改查")
@RestController
@RequestMapping("user")
public class JpaComplexController {

    @Autowired
    private UserServiceImpl userService;


    /**
     * 新增,单表
     * @param users
     * @return
     */
    @ApiOperation(value = "添加一条记录", notes = "通过对象的方式进行接收与添加")
    @PostMapping("add")
    public Users addUser(Users users){
        Users users1 = new Users();
        users1.setUsername(users.getUsername());
        users1.setUserage(users.getUserage());
        userService.saveUser(users1);
        System.out.println("添加成功");
        return users1;
    }

    @ApiOperation(value = "根据id删除用户", notes = "根据Id主键删除对象")
    @DeleteMapping("delete")
    private String deleteUserById(Integer id){
        userService.deleteUserById(id);
        return "Success";
    }

    @ApiOperation(value = "更新用户的名字", notes = "根据Id主键查询User对象,再更新其名字")
    @PutMapping("update")
    public Users updateUser(Integer id,String nickName){
        Users user = userService.findUser(id);
        user.setUsername(nickName);
        userService.updateUser(user);
        return user;
    }

    @ApiOperation(value = "根据id查询User对象", notes = "根据主键查询User对象")
    @GetMapping("find")
    private Users findUser(Integer id){
        return userService.findUser(id);

    }

    @ApiOperation(value = "批量添加", notes = "使用EntityManager持久层管理器批量更新")
    @PostMapping("addMany")
    private String addAll(List<Users> users){
        userService.addMany(users);
        return "success";
    }



}
