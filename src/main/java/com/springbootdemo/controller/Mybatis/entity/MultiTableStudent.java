package com.springbootdemo.controller.Mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 每个学生都存在一个教师Id,以此来标记是哪个老师
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MultiTableStudent {

    private int id;
    private String name;
    private int tid;

    //每个学生都存在一个教师属性,
    private MultiTableTeacher  teacher;
}
