package com.springbootdemo.controller.Mybatis.mapper;

import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;

import java.util.List;

/**
 * Dao接口
 */
public interface UserDaoMapper {
    //插入
    public void insert(MybatisDemoUser user);
    //根据id删除
    public void delete(Integer id);
    //根据user的id修改
    public boolean update(MybatisDemoUser user);

    //根据id查询
    public  MybatisDemoUser getByName(String name);

    //查询全部
    List<MybatisDemoUser> getUserList();
}
