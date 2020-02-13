package com.springbootdemo.controller.Mybatis.IDutils;

import java.util.UUID;

/**
 * 主键使用随机的UUID
 */
public class IDutils {
    public static String getId(){

        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
