package com.springbootdemo.controller.Mybatis.mapper;

import com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MultiTableTeacherMapper {

    //根据id获取老师及其所有学生 @param可以将形参传递给xml,xml使用#{}取出,key是@parm规定的键值
    MultiTableTeacher getTeahcer(@Param("tid") int id);


    MultiTableTeacher getTeahcerByQuery(@Param("tid") int id);
}
