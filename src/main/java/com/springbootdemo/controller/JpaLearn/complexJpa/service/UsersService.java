package com.springbootdemo.controller.JpaLearn.complexJpa.service;

import com.springbootdemo.controller.JpaLearn.complexJpa.model.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsersService {

    Users saveUser(Users users);
    void deleteUser(Users users);
    void deleteUserById(Integer id);
    Users updateUser(Users users);
    Users findUser(Integer id);
    Users saveOrFlush(Users users);
    void addMany(List<Users> users);
    Page<Users> FindallPage(Integer page, Integer size,String params);
    Long countNumebr(Users users);
}
