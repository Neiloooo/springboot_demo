package com.springbootdemo.controller.Mybatis.Service;

import com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher;

public interface MultiTableTeacherService {

    //根据id获取老师及其所有学生 @param可以将形参传递给xml,xml使用#{}取出,key是@parm规定的键值
    MultiTableTeacher getTeahcer(int id);


}
