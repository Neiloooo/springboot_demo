package com.springbootdemo.controller.Security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


/**
 * 实现UserDetailS接口后,必须实现其几个方法
 * 必须实体类,然后实现UserDetails接口,交由SpringSecurity控制
 */

@Slf4j
public class SecurityUser extends Security_User_model implements UserDetails {
    private static final long serialVersionUID = -8086897203124221305L; //验证序列化后的版本控制

    /**
     * 自定义构造方法,把User实体数据存入Security的核心组件(UserDetails中)
     * @param userModel
     */
    SecurityUser(Security_User_model userModel){
        if (userModel!=null){
            this.setUserId(userModel.getUserId());
            this.setUsername(userModel.getUsername());
            this.setPassword(userModel.getPassword());
            this.setSex(userModel.getSex());
            this.setRoles(userModel.getRoles());
        }
    }

    /**
     * 权限合集
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
