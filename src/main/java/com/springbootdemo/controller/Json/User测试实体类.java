package com.springbootdemo.controller.Json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


/**
 *
 * 	2、jackson处理相关自动
 * 		指定字段不返回：@JsonIgnore
 * 		指定日期格式：@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GMT+8")
 * 		空字段不返回：@JsonInclude(Include.NON_NUll)
 * 		指定别名：@JsonProperty
 */

public class User测试实体类 {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String age; //字段为空不返回(为null)

    @JsonIgnore
    private String pwd; //jackJson的注解,让实体类的该属性不会再序列化

    @JsonProperty("account")
    private String phone; //重命名,不把实体类中的命名暴露给外面

    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Date createTime; //格式化时间,把原始的时间格式,转换成我们需要的时间格式



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User测试实体类(String age, String pwd, String phone, Date createTime) {
        this.age = age;
        this.pwd = pwd;
        this.phone = phone;
        this.createTime = createTime;
    }
}
