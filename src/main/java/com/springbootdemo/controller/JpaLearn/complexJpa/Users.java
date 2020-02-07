package com.springbootdemo.controller.JpaLearn.complexJpa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "t_users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //主键唯一且自增长,数据不是特别大的时候可以使用
    @Column(name = "userid") //绑定数据库的userid字段,如果相同可以不写该注解
    private Integer userid;

    private String username;
    private Integer userage;

}
