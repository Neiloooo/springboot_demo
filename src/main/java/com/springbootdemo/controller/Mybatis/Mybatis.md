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

## 三、复杂查询(一对一,一对多,Mybatis的多对多可以看成双端一对多)

**结构类型:**

​			**一个老师对应多个学生,一个学生对应一个老师**

#### 一对一与一对多这里就写一起了

### **3.1实体:**

```java
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
```

```java
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
```

### 3.2Dao层接口

```java
package com.springbootdemo.controller.Mybatis.mapper;


import com.springbootdemo.controller.Mybatis.entity.MultiTableStudent;

import java.util.List;

/**
 * 多表查询的教师Mapper
 */
public interface MultiTableStudentMapper {
    //查询所有的学生信息,以及对应的老师的信息,多表管理,
    // 一个老师对应一个学生
    //变相的一对一
    //1.子查询
    public List<MultiTableStudent> getStudent();

    //按照结果嵌套映射
    public List<MultiTableStudent> getStudentByResult();




}
```

```java
package com.springbootdemo.controller.Mybatis.mapper;

import com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MultiTableTeacherMapper {

    //根据id获取老师及其所有学生 @param可以将形参传递给xml,xml使用#{}取出,key是@parm规定的键值
    MultiTableTeacher getTeahcer(@Param("tid") int id);


    MultiTableTeacher getTeahcerByQuery(@Param("tid") int id);
}
```

### 3.3Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.springbootdemo.controller.Mybatis.mapper.MultiTableStudentMapper">

<!--一对一-->
<!--    思路1:(两个sql),子查询-->
<!--        ①查询出所有的学生信息-->
<!--        ②根据查询出来的学生tid,寻找对应的老师-->

<!--    查询出所有学生等信息包括tid-->
    <select id="getStudent" resultMap="StudentTeacher">
        select * from student
    </select>

<!--    建立查询结果的映射关系,执行子查询-->
    <resultMap id="StudentTeacher" type="com.springbootdemo.controller.Mybatis.entity.MultiTableStudent">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
<!--        复杂的属性,我们需要单独处理 对象:association 集合:collection-->
<!--        根据查出来的tid,执行嵌套在里面的子查询-->
        <association property="teacher" column="tid" javaType="com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher" select="getTeacher"/>
    </resultMap>

<!--    根据教师ID查询教师,通过上面的映射关系掺入参数    -->
    <select id="getTeacher" resultType="com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher">
        select
        *
        FROM
        teacher
        where
        id =#{id}
    </select>


    <!--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
<!--二、按照结果嵌套处理(常用)-->
    <select id="getStudentByResult" resultMap="StudentTeacherByResutlt">
        select
        s.id sid,
        s.name sname,
        t.name tname
        FROM
        student s,
        teacher t
        where s.tid=t.id;
    </select>

    <resultMap id="StudentTeacherByResutlt" type="com.springbootdemo.controller.Mybatis.entity.MultiTableStudent">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <association property="teacher" javaType="com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher">
            <result property="name" column="tname"/>
        </association>
    </resultMap>



</mapper>
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.springbootdemo.controller.Mybatis.mapper.MultiTableTeacherMapper">
<!--一对多-->
<!--  1.  按结果嵌套查询-->
    <select id="getTeahcer" resultMap="TeacherStudentList">
        select s.id sid,s.name sname,t.name tname,t.id tid
        from
        student s,teacher t
        where  s.tid=t.id and t.id=#{tid}
    </select>

<!--    一对多的结果集映射,coolection-->
    <resultMap id="TeacherStudentList" type="com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>
<!--        复杂的属性,需要我们单独处理 对象:association 集合:collection-->
<!--        javaType: 制定的属性类型(对象的类型)-->
<!--        集合中的泛型信息,我们使用ofType-->
        <collection property="multiTableStudents" ofType="com.springbootdemo.controller.Mybatis.entity.MultiTableStudent">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="tid"/>
        </collection>
    </resultMap>

    <!--    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
<!--  2.  通过子查询的方式映射-->
    <select id="getTeahcerByQuery" resultMap="TeacherStudentList2">
        select * from teacher where  id=#{tid};
    </select>

    <resultMap id="TeacherStudentList2" type="com.springbootdemo.controller.Mybatis.entity.MultiTableTeacher">
        <collection property="multiTableStudents"  ofType="com.springbootdemo.controller.Mybatis.entity.MultiTableStudent"
                    select="getStudentByTeacherId" column="id"/>
    </resultMap>
    <select id="getStudentByTeacherId" resultType="com.springbootdemo.controller.Mybatis.entity.MultiTableStudent">
        select  * FROM  student where tid= #{tid}
    </select>



</mapper>
```

## 四、动态sql(if(and),Choose(or),where,when(java中的while),set(动态更新),Foreach(java的for循环)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.springbootdemo.controller.Mybatis.mapper.BlogMapper">


    <insert id="insertBlog" parameterType="com.springbootdemo.controller.Mybatis.entity.Blog">
        insert  into blog(id,title,author,create_time,views)
        values (#{id},#{title},#{author},#{creatTime},#{views});
    </insert>

<!--    通过在xml中写if标签,提高sql的复用性-->
<!--    这个select的意思是,如果前端传入了title就在查询的条件加上title条件-->
<!--    如果传入author,就在查询条件加入author条件-->
    <select id="queryBlogIf" resultType="com.springbootdemo.controller.Mybatis.entity.Blog">
        select * from blog where 1=1
        <if test="title !=null ">
            and title =#{title}
        </if>
        <if test="author !=null ">
            and author = #{author}
        </if>
    </select>



<!--    有时候我们并不想应用所有的条件，而只是想从多个选项中选择一个。-->
<!--    而使用if标签时，只要test中的表达式为 true，就会执行 if 标签中的条件。-->
<!--    MyBatis 提供了 choose 元素。if标签是与(and)的关系，而 choose 是或(or)的关系-->
    <select id="queryBlogChoose" parameterType="com.springbootdemo.controller.Mybatis.entity.Blog" resultType="com.springbootdemo.controller.Mybatis.entity.Blog">
        select * FROM  blog
        <where>
            <choose>
                <when test="author != null and author != ''">
                    and author=#{author}
                </when>
                <otherwise>
                    and views= #{views}
                </otherwise>
            </choose>
        </where>
    </select>



<!--    set标签会动态设置set关键字,同时也会删除无关逗号-->
<!--    动态更新,谁变更谁-->
    <update id="updateBlog" parameterType="com.springbootdemo.controller.Mybatis.entity.Blog"  >
        update bllog
        <set>
            <include refid="if-title-author"></include>
        </set>
        where id=#{id}
    </update>


<!--    将一部分sql提取出来,方便复用,使用include标签的refid进行复用-->
    <sql id="if-title-author">
        <if test="title != null and title != ''">
            title=#{title}
        </if>
        <if test="author != null and author != ''">
            author=#{author}
        </if>
    </sql>

<!--
    sql:select * from blog where 1=1 and (id=1 or id = 2 or id = 3)
        Foreach标签(For循环)
        批量增删改查,非全部
        -->
    <select id="queryBlogForeach" parameterType="java.util.Map"  resultType="com.springbootdemo.controller.Mybatis.entity.Blog">
        select * from blog
        <where>
            <foreach collection="ids" item="id" open="and (" close=")" separator="or">
                id=#{id}
            </foreach>
        </where>
    </select>

</mapper>
```