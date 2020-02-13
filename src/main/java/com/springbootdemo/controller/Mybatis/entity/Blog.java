package com.springbootdemo.controller.Mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 动态sql专用博客表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {

    private String id;
    private String title;
    private String author;
    private Date creatTime; //属性名与数据库不一致可以开启驼峰模式自动匹配
    private int views;
}
