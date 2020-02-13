package com.springbootdemo.controller.Mybatis.Controller;

import com.springbootdemo.controller.Mybatis.Service.MultiTableTeacherService;
import com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Mybatis的复杂查询")
@RestController
@RequestMapping("MultiTable")
public class MultiTableTeacherController {


    @Autowired
    private MultiTableTeacherService multiTableTeacherService;

    @ApiOperation(value = "一对多查询,结果集映射", notes = "一个老师对应多个学生,获取教师的全部信息及其学生的全部信息")
    @GetMapping("/getStudent/{id}")
    public MultiTableTeacher getTeahcer(int id) {
        return multiTableTeacherService.getTeahcer(id);
    }

}
