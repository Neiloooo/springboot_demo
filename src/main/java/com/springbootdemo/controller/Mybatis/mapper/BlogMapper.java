package com.springbootdemo.controller.Mybatis.mapper;

import com.springbootdemo.controller.Mybatis.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {


    //普通插入
    int insertBlog(Blog blog);

    //Mybatis中if语句的使用
    //条件查询,查询条件不固定
    //一条Mapper实现不同sql的功能
    List<Blog> queryBlogIf(Blog blog);

    //Choose标签的使用+Where标签(保护sql安全)+when标签(当什么什么时)
    List<Blog> queryBlogChoose(Blog blog);

    //更新 +set标签
    int updateBlog(Blog blog);

    //Foreach标签演示(查询id为1,2,3,号的博客)
    List<Blog> queryBlogForeach(Map ids);
}
