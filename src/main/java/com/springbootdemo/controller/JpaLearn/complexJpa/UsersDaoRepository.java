package com.springbootdemo.controller.JpaLearn.complexJpa;

import com.springbootdemo.controller.JpaLearn.complexJpa.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositroy的Dao接口
 */
public interface UsersDaoRepository extends JpaRepository<Users,Integer> {
    /**
     * 方法命名规则实现sql的简单操作,就是拿关键字拼接sql
     *
     */

    //通过用户名查
    List<Users> findByUsernameIs(String name);
    //通过用户名模糊查
    List<Users> findByUsernameLike(String string);
    //查出用户名和用户年龄大于多少的用户
    List<Users> findByUsernameAndUserageGreaterThanEqual(String name,Integer age);
    //查询年龄为24岁的数量
    Long countByUserage(Integer age);
    //根据年龄删除用户
    Long deleteByUserage(Integer age);
    //获取符合条件的前10条数据
    List<Users> findFirst2ByUsername(String name);


}
