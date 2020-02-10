package com.springbootdemo.controller.Security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 登陆的User表
 */
@Data
@Entity
@Table(name = "t_securityUser")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Security_User_model implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //主键唯一且自增长,数据不是特别大的时候可以使用
    @Column(name = "userid") //绑定数据库的userid字段,如果相同可以不写该注解
    private Long userId;

    private String username;
    private String password;
    private String realname;
    private String sex;
    private String roles;
}
