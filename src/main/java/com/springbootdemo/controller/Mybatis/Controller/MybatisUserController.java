package com.springbootdemo.controller.Mybatis.Controller;

import com.github.pagehelper.PageInfo;
import com.springbootdemo.controller.Mybatis.Service.MybatisDemoUserImpl;
import com.springbootdemo.controller.Mybatis.Service.MybatisUser;
import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;
import com.springbootdemo.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "Mybatis的单表增删改查")
@RestController
@RequestMapping("Mybatis")
public class MybatisUserController {
    @Autowired
    private MybatisUser mybatisUser;

    @ApiOperation(value = "添加一条记录", notes = "通过对象的方式进行接收与添加")
    @PostMapping("/Insert_user")
    public void insert( MybatisDemoUser user) {

        mybatisUser.insert(user);
    }

    @ApiOperation(value = "删除记录", notes = "restful风格从url获取id,根据id删除")
    //根据id删除
    @DeleteMapping("/deluser/{id}")
    public void delete(@PathVariable("id") Integer id) {
        mybatisUser.delete(id);
    }

    @ApiOperation(value = "修改", notes = "restful风格从url获取id,根据id修改")
    //修改
    @PutMapping("/updateuser")
    public void update(MybatisDemoUser user) {

        mybatisUser.update(user);
    }

    @ApiOperation(value = "查询单个", notes = "restful风格从url获取id,根据name查询")
    //根据id查询学生
    @GetMapping("/getuserById/{name}")
    public MybatisDemoUser getById(String name) {
        MybatisDemoUser user1 = mybatisUser.getByName(name);
        return user1;
    }

    @ApiOperation(value = "查询全部", notes = "查询全部,返回List,交给jackjson进行序列化")
    //查询全部
    @GetMapping("/GetAllUser")
    public List<MybatisDemoUser> GetAllUser(){
        List<MybatisDemoUser> users = mybatisUser.getUserList();
        return users;
    }

    @ApiOperation(value = "分页查询全部返回PageInfo", notes = "使用PageHelper分页查询全部,返回pageInfo对象,什么都有但是很乱")
    @GetMapping("/getPageInfo/{pageNum}{pageSize}")
    public Result getPageInfo(Integer pageNum,Integer pageSize){
        PageInfo<MybatisDemoUser> queryResult = mybatisUser.findAllUserByPages(pageNum, pageSize);
        return Result.success(queryResult);
    }

    @ApiOperation(value = "分页查询全部返回List", notes = "使用PageHelper分页查询全部,返回List对象,只有数据,没有当前页数与个数")
    @GetMapping("/getPageList")
    public List<MybatisDemoUser> testPageHelper2(){
        List<MybatisDemoUser> queryResult = mybatisUser.findAllUserByPageF(1, 5);
        return queryResult;
    }


}
