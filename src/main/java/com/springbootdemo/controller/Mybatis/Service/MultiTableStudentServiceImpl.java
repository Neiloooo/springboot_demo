package com.springbootdemo.controller.Mybatis.Service;

import com.springbootdemo.controller.Mybatis.entity.MultiTableStudent;
import com.springbootdemo.controller.Mybatis.mapper.MultiTableStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MultiTableStudentServiceImpl implements MultiTableStudentService {

    @Autowired
    MultiTableStudentMapper multiTableStudentMapper;

    @Override
    public List<MultiTableStudent> getStudent() {
        return multiTableStudentMapper.getStudent();
    }

    @Override
    public List<MultiTableStudent> getStudentByResult() {
        return multiTableStudentMapper.getStudentByResult();
    }
}
