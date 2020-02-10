package com.springbootdemo.controller.Mybatis.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;
import com.springbootdemo.controller.Mybatis.mapper.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MybatisDemoUserImpl implements MybatisUser {

    @Autowired
    private UserDaoMapper userDao;

    @Override
    public void insert(MybatisDemoUser user) {
        userDao.insert(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public boolean update(MybatisDemoUser user) {
        boolean flage = userDao.update(user);
        return flage;
    }

    @Override
    public MybatisDemoUser getByName(String name) {
        MybatisDemoUser user = userDao.getByName(name);
        return user;
    }

    @Override
    public List<MybatisDemoUser> getUserList() {
        List<MybatisDemoUser> userList = userDao.getUserList();
        return userList;
    }

    //分页查询返回的是List
    @Override
    public List<MybatisDemoUser> findAllUserByPageF(int pageNum, int pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        List<MybatisDemoUser> userList = userDao.getUserList();
        return userList;
    }

    //分页查询返回的是PageInfo对象
    @Override
    public PageInfo<MybatisDemoUser> findAllUserByPages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MybatisDemoUser> userList = userDao.getUserList();
        //开始分页后直接new PageInfo对象,将list放进去
        PageInfo<MybatisDemoUser> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
