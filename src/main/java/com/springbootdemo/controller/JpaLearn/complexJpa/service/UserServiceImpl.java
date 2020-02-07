package com.springbootdemo.controller.JpaLearn.complexJpa.service;

import com.springbootdemo.controller.JpaLearn.UserRepository;
import com.springbootdemo.controller.JpaLearn.complexJpa.Users;
import com.springbootdemo.controller.JpaLearn.complexJpa.UsersDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jnlp.SingleInstanceListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UsersService  {
    //注入Dao层接口
    @Autowired
    private UsersDaoRepository usersDaoRepository;

    @Override
    public Users saveUser(Users users) {
        Users users1 = usersDaoRepository.save(users);
        return users1;
    }

    @Override
    public void deleteUser(Users users) {
        usersDaoRepository.delete(users);
    }

    @Override
    public void deleteUserById(Integer id) {
        usersDaoRepository.deleteById(id);
    }

    @Override
    public Users updateUser(Users users) {
        return usersDaoRepository.save(users);
    }

    //.get是判null用的,null抛异常,非null返回
    @Override
    public Users findUser(Integer id) {
        return usersDaoRepository.findById(id).get();
    }

    //保存或更新
    @Override
    public Users saveOrFlush(Users users) {
        return usersDaoRepository.saveAndFlush(users);
    }

    //批量更新,首先获取持久层管理器
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addMany(List<Users> users) {
        for (int i = 0;i<users.size();i++){
            em.merge(users.get(i));
            if (i%30==0){
                em.flush();
                em.clear();
            }

        }
    }


}
