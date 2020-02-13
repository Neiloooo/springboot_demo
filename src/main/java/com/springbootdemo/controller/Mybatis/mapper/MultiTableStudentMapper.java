package com.springbootdemo.controller.Mybatis.mapper;


import com.springbootdemo.controller.Mybatis.entity.MultiTableStudent;

import java.util.List;

/**
 * 多表查询的教师Mapper
 */
public interface MultiTableStudentMapper {
    //查询所有的学生信息,以及对应的老师的信息,多表管理,
    // 一个老师对应一个学生
    //变相的一对一
    //1.子查询
    public List<MultiTableStudent> getStudent();

    //按照结果嵌套映射
    public List<MultiTableStudent> getStudentByResult();




}
