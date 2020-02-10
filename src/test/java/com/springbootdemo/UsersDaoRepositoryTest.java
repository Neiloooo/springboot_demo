package com.springbootdemo;

import com.springbootdemo.controller.JpaLearn.complexJpa.model.Users;
import com.springbootdemo.controller.JpaLearn.complexJpa.UsersDaoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UsersDaoRepositoryTest {

    @Autowired
    private UsersDaoRepository usersDaoRepository;

    /**
     * 需求,使用用户名作为查询条件
     */
    @Test
    public void test1(){
        /**
         * 判断相等的条件,有三种表达方式
         * 1.什么都不写,默认的就是做相等判断
         * 2.IS
         * 3.Equal
         */
        List<Users> list = this.usersDaoRepository.findByUsernameIs("张三");
        for (Users users:list){
            System.out.println(users);
        }
    }



}
