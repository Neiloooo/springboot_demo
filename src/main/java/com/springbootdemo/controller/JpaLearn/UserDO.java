package com.springbootdemo.controller.JpaLearn;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 数据库表的映射实体类
 * 建立基于 POJO 的实体对象，需要注意的是 JPA 与 Mybatis 是有区别的
 * 实体类需要使用 @Entity 注解标注
 * 需要对实体类的属性进行标注，使用 @Id 标注主键
 * 使用 @Column 标注非主键
 */
@Entity
@Table(name = "t_user")
@Data
public class UserDO {

    @Id
    private Integer id;
    @Column(name = "user_name",length = 200)
    private String username;
    @Column(name = "password",length = 200)
    private String password;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "last_login_time")
    private Date lastLoginTime;


}
