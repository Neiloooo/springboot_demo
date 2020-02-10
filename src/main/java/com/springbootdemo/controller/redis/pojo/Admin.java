package com.springbootdemo.controller.redis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * redis存入对象测试,一定要序列化
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin implements Serializable {
    private static final long serialVersionUID=-1L; //验证序列化一致性

    private String username;
    private Integer age;


}
