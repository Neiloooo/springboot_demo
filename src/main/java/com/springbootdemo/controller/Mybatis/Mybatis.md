# Mybatis

**最重要:用的人多**

​			编码:

​				1.实体类

​				2.Dao接口

​				3.Service层接口

​				4.Service层接口实现类

​				5.Controller层

推荐安装Mybatis插件,引入swagger2,使用pageHelper插件

## 一、引入Mybatis

### 1.1引入依赖

```xml
<!--        整合Mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>
<!--        整合分页插件-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.5</version>
        </dependency>
```

### 1.2配置文件

```yml
server:
  port: 8090 #端口
  #解决中文乱码问题
  tomcat:
    uri-encoding: UTF-8
http:
  encoding:
    charset: UTF-8
    force: true
    enabled: true
	#解决中文乱码问题
spring:
  application:
    name: springdemo  # 项目名称
  #通用的数据源配置
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/jpadata?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: root
    
mybatis:
  #Mapper的位置
  mapper-locations: classpath:mybatis/*.xml
  #Mybatis实体位置,也就是别名,可用可不用
  type-aliases-package: com.springbootdemo.controller.Mybatis.entity
  # Mybatis SQL语句控制台打印
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
#pagehelper分页插件
pagehelper:
  helperDialect: mysql
  reasonable: true # #启用合理化时，如果pageNum<1会查询第一页，如果pageNum>总页数会查询最后一页； #禁用合理化时，如果pageNum<1或pageNum>总页数会返回空数据。
  supportMethodsArguments: true #支持通过Mapper接口参数来传递分页参数，默认值false，分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值，查找到合适的值时就会自动分页。
  params: count=countSql #为了支持startPage(Object params)方法，增加了该参数来配置参数映射，用于从对象中根据属性名取值; 可以配置 pageNum,pageSize,count,pageSizeZero,reasonable，不配置映射的用默认值， 默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero。
```

### 1.3启动类引入Mapper.xml的位置

```java
package com.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan({"com.springbootdemo.controller.Mybatis.mapper"})//指定mapper所在目录，或者在mapper上添加@Mapper注解
@SpringBootApplication
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

}
```

## 二、Mybatis的单表增删改查与分页

### 2.1实体类pojo:

```java
package com.springbootdemo.controller.Mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MybatisDemoUser {
    private Integer id;
    private String name;
    private String pwd;
}
```

### 2.2Dao层接口

```java
package com.springbootdemo.controller.Mybatis.mapper;

import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;

import java.util.List;

/**
 * Dao接口
 */
public interface UserDaoMapper {
    //插入
    public void insert(MybatisDemoUser user);
    //根据id删除
    public void delete(Integer id);
    //根据user的id修改
    public boolean update(MybatisDemoUser user);

    //根据id查询
    public  MybatisDemoUser getByName(String name);

    //查询全部
    List<MybatisDemoUser> getUserList();
}
```

### 2.3Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace必须指向Dao接口 -->
<mapper namespace="com.springbootdemo.controller.Mybatis.mapper.UserDaoMapper">
    <insert id="insert" parameterType="com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser" >
          insert into user
        (name ,pwd)values(#{name},#{pwd});
    </insert>

    <update id="update" parameterType="com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser" >
    update   user  set name=#{name},pwd=#{pwd} where id=#{id}
    </update>

    <delete id="delete" parameterType="integer">
        delete from user where id=#{id}
    </delete>

    <select id="getUserList"  parameterType="integer" resultType="com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser">
        select * from user
    </select>

    <select id="getByName" parameterType="String" resultType="com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser">
        select  * from user where name =#{name}
    </select>
</mapper>
```

### 2.4Service层接口

```java
package com.springbootdemo.controller.Mybatis.Service;

import com.github.pagehelper.PageInfo;
import com.springbootdemo.controller.JpaLearn.complexJpa.model.Users;
import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;

import java.util.List;

public interface MybatisUser  {

    //插入
    public void insert(MybatisDemoUser user);
    //根据id删除
    public void delete(Integer id);
    //根据user的id修改
    public boolean update(MybatisDemoUser user);
    //根据id查询
    public MybatisDemoUser getByName(String name);
    //查询全部
    List<MybatisDemoUser> getUserList();

    //返回的是list对象
    List<MybatisDemoUser> findAllUserByPageF(int pageNum,int pageSize);

    //分页插件分页,返回的是PageInfo对象
    PageInfo<MybatisDemoUser> findAllUserByPages(int pageNum, int pageSize);
}
```

### 2.5Service层实现类

```java
package com.springbootdemo.controller.Mybatis.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;
import com.springbootdemo.controller.Mybatis.mapper.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MybatisDemoUserImpl implements MybatisUser {

    @Autowired
    private UserDaoMapper userDao;

    @Override
    public void insert(MybatisDemoUser user) {
        userDao.insert(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public boolean update(MybatisDemoUser user) {
        boolean flage = userDao.update(user);
        return flage;
    }

    @Override
    public MybatisDemoUser getByName(String name) {
        MybatisDemoUser user = userDao.getByName(name);
        return user;
    }

    @Override
    public List<MybatisDemoUser> getUserList() {
        List<MybatisDemoUser> userList = userDao.getUserList();
        return userList;
    }

    //分页查询返回的是List
    @Override
    public List<MybatisDemoUser> findAllUserByPageF(int pageNum, int pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        List<MybatisDemoUser> userList = userDao.getUserList();
        return userList;
    }

    //分页查询返回的是PageInfo对象
    @Override
    public PageInfo<MybatisDemoUser> findAllUserByPages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MybatisDemoUser> userList = userDao.getUserList();
        //开始分页后直接new PageInfo对象,将list放进去
        PageInfo<MybatisDemoUser> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
```

### 2.6Controller层

```java
package com.springbootdemo.controller.Mybatis.Controller;

import com.github.pagehelper.PageInfo;
import com.springbootdemo.controller.Mybatis.Service.MybatisDemoUserImpl;
import com.springbootdemo.controller.Mybatis.Service.MybatisUser;
import com.springbootdemo.controller.Mybatis.entity.MybatisDemoUser;
import com.springbootdemo.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "Mybatis的单表增删改查")
@RestController
@RequestMapping("Mybatis")
public class MybatisUserController {
    @Autowired
    private MybatisUser mybatisUser;

    @ApiOperation(value = "添加一条记录", notes = "通过对象的方式进行接收与添加")
    @PostMapping("/Insert_user")
    public void insert( MybatisDemoUser user) {

        mybatisUser.insert(user);
    }

    @ApiOperation(value = "删除记录", notes = "restful风格从url获取id,根据id删除")
    //根据id删除
    @DeleteMapping("/deluser/{id}")
    public void delete(@PathVariable("id") Integer id) {
        mybatisUser.delete(id);
    }

    @ApiOperation(value = "修改", notes = "restful风格从url获取id,根据id修改")
    //修改
    @PutMapping("/updateuser")
    public void update(MybatisDemoUser user) {

        mybatisUser.update(user);
    }

    @ApiOperation(value = "查询单个", notes = "restful风格从url获取id,根据name查询")
    //根据id查询学生
    @GetMapping("/getuserById/{name}")
    public MybatisDemoUser getById(String name) {
        MybatisDemoUser user1 = mybatisUser.getByName(name);
        return user1;
    }

    @ApiOperation(value = "查询全部", notes = "查询全部,返回List,交给jackjson进行序列化")
    //查询全部
    @GetMapping("/GetAllUser")
    public List<MybatisDemoUser> GetAllUser(){
        List<MybatisDemoUser> users = mybatisUser.getUserList();
        return users;
    }

    @ApiOperation(value = "分页查询全部返回PageInfo", notes = "使用PageHelper分页查询全部,返回pageInfo对象,什么都有但是很乱")
    @GetMapping("/getPageInfo/{pageNum}{pageSize}")
    public Result getPageInfo(Integer pageNum,Integer pageSize){
        PageInfo<MybatisDemoUser> queryResult = mybatisUser.findAllUserByPages(pageNum, pageSize);
        return Result.success(queryResult);
    }

    @ApiOperation(value = "分页查询全部返回List", notes = "使用PageHelper分页查询全部,返回List对象,只有数据,没有当前页数与个数")
    @GetMapping("/getPageList")
    public List<MybatisDemoUser> testPageHelper2(){
        List<MybatisDemoUser> queryResult = mybatisUser.findAllUserByPageF(1, 5);
        return queryResult;
    }


}
```