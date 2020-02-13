package com.springbootdemo.controller.Mybatis.Service;

import com.springbootdemo.controller.Mybatis.entity.MultiTableStudent;

import java.util.List;

public interface MultiTableStudentService {
    public List<MultiTableStudent> getStudent();

    //按照结果嵌套映射
    public List<MultiTableStudent> getStudentByResult();
}
