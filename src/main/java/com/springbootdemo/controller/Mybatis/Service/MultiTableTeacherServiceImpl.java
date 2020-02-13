package com.springbootdemo.controller.Mybatis.Service;

import com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher;
import com.springbootdemo.controller.Mybatis.mapper.MultiTableTeacherMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class MultiTableTeacherServiceImpl implements MultiTableTeacherService {

    @Autowired
    MultiTableTeacherMapper multiTableTeacherMapper;

    @Override
    public MultiTableTeacher getTeahcer(int id) {
        return multiTableTeacherMapper.getTeahcer(id);
    }
}
