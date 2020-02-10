package com.springbootdemo;

import com.springbootdemo.controller.JpaLearn.complexJpa.model.Users;
import com.springbootdemo.controller.JpaLearn.complexJpa.UsersDaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class ComplexJpaTest {

    @Autowired
    private UsersDaoRepository usersDaoRepository;

    @Test
    @Transactional //在测试类对于事务的提交方式默认是回滚
    @Rollback(false) //取消自动回滚
    public void testInsertUsuers(){
        Users users = new Users();
        users.setUserage(24);
        users.setUsername("张三");
        usersDaoRepository.save(users);

    }

}
