package com.springbootdemo.controller.JpaLearn;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 新建仓库接口类UserRepository(Dao层接口)
 * 仓库接口类 UserRepository 就是我们常用的 Dao 接口，需要注意的是 JPA 的仓储接口需要
 * 1.使用 @Repository 注解
 * 2.继承 JPARepository<类名,主键类型>
 * 3.UserRepository 不需要编写任何代码，就可实现增删改查(单表)
 */

@Repository
public interface UserRepository extends JpaRepository<UserDO,Integer>  {



}
