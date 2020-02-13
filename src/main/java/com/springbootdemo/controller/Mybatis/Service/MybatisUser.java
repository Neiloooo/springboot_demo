package com.springbootdemo.controller.Mybatis.Service;

import com.github.pagehelper.PageInfo;
import com.springbootdemo.controller.JpaLearn.complexJpa.model.Users;
import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;

import java.util.List;

public interface MybatisUser  {

    //插入
    public void insert(MybatisDemoUser user);
    //根据id删除
    public void delete(Integer id);
    //根据user的id修改
    public boolean update(MybatisDemoUser user);
    //根据id查询
    public MybatisDemoUser getByName(String name);
    //查询全部
    List<MybatisDemoUser> getUserList();

    //返回的是list对象
    List<MybatisDemoUser> findAllUserByPageF(int pageNum,int pageSize);

    //分页插件分页,返回的是PageInfo对象
    PageInfo<MybatisDemoUser> findAllUserByPages(int pageNum, int pageSize);

    //模糊查询
    List<MybatisDemoUser> getUserLike(String name);
}
