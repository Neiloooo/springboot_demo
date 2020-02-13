package com.springbootdemo.controller.Mybatis.Controller;

import com.springbootdemo.controller.Mybatis.Service.MultiTableStudentService;
import com.springbootdemo.controller.Mybatis.Service.MybatisUser;
import com.springbootdemo.controller.Mybatis.entity.MultiTableStudent;
import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Mybatis的复杂查询")
@RestController
@RequestMapping("MultiTable")
public class MultiTableStudentController {

    @Autowired
    private MultiTableStudentService multiTableStudentService;

    @ApiOperation(value = "一对一查询,子查询嵌套", notes = "一个学生对应一个教师,获取学生的全部信息及其教师的全部信息")
    @GetMapping("/getStudent")
    public List<MultiTableStudent> getStudent() {
       return multiTableStudentService.getStudent();
    }


    @ApiOperation(value = "一对一查询,结果集映射(常用)", notes = "一个学生对应一个教师,获取学生的全部信息及其教师的全部信息")
    @GetMapping("/getStudentResult")
    public List<MultiTableStudent> getStudentResult() {

        return multiTableStudentService.getStudentByResult();
    }


}
