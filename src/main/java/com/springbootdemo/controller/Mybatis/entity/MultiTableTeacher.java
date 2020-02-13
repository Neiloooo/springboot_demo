package com.springbootdemo.controller.Mybatis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 与学生一对多的老师类
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class MultiTableTeacher {
    private int id;
    private String name;
    //一个老师拥有多个学生
    List<MultiTableStudent> multiTableStudents;
}
