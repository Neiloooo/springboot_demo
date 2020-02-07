package com.springbootdemo;

import com.springbootdemo.controller.JpaLearn.UserDO;
import com.springbootdemo.controller.JpaLearn.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    /**
     * 增
     */
    @Test
    public void before(){
        UserDO userDO = new UserDO();
        userDO.setId(1);
        userDO.setUsername("尼玛死了");
        userDO.setSex(1);
        userDO.setLastLoginTime(new Date());
        userDO.setPassword("password");
        userRepository.save(userDO);
    }

    /**
     * 根据id查
     */
    @Test
    public void testFind(){
        Optional<UserDO> optionalUserDo = userRepository.findById(1);
        //查出来不是null
        if (optionalUserDo.isPresent()){
            //将查出来的转变为对象
            UserDO userDO = optionalUserDo.get();
            System.out.println("这是啥?"+userDO.getPassword());
        }
    }

    /**
     * 查询所有数据,并且挨个遍历
     */
    @Test
    public void testFindAll(){
        List<UserDO> ListAll = userRepository.findAll();
        for (UserDO user:ListAll){
            System.out.println("user_name:"+user.getPassword());
        }
    }

    /**
     * 更新
     */
    @Test
    public void testUpdate(){
        Optional<UserDO> optionalUserDO = userRepository.findById(1);
        if (optionalUserDO.isPresent()){
            UserDO userDO = optionalUserDO.get();
            userDO.setUsername("NMSL");
            userRepository.save(userDO);
            System.out.println("testFinde user: "+userDO.getUsername());
        }
    }

    @Test
    public void dele(){
        userRepository.deleteById(1);
    }
}
