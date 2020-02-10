package com.springbootdemo.controller.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * 自定义Security策略类,AOP: 拦截器
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource; //从数据库查询需要注入数据源

    @Autowired
    private LoginFailureHandler failureHandler;

    @Autowired
    private LoginSuccessHandler successHandler;


    //重写默认的Spring Securiy的配置文件 链式编程
    //请求授权的规则
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
//                .anyRequest().permitAll()
                .antMatchers("/Mybatis/**").permitAll()
                .and().logout().permitAll();

    }

//                .antMatchers("/").permitAll() //首页所有人都能访问
//                .antMatchers("/Mybatis/**", "/actuator/**").permitAll()
//                .antMatchers("/v2/api-docs", "/doc.html", "/configuration/ui",
//                        "/swagger-resources", "/configuration/security", "/swagger-ui.html",
//                        "/webjars/**", "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html");

//
//        http.formLogin();
//        //没有权限默认会开启登录页面,使用loginPage(跳转到我们自己写的首页,或Api?)
//        http.formLogin().loginPage("/login")
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
//        //注销功能 也可以删除cookie,自定义logout的地址
//        //正常注销后跳到首页
//                .and()
//                .logout()
//                .logoutSuccessUrl("/home");
//        //开启记住我功能,cookie,默认保存两周
//        http.rememberMe();
    }

    //认证的规则,可以从数据库中查,也可以从内存中取
    //springboot2.1.x可以直接使用,但是后面的版本需要密码加密后才能使用
    //密码编码: PasswordEncoder
    //在Spring Security 5.0+中新增了很多的加密方法
//    @Override
//    protected  void  configure(AuthenticationManagerBuilder auth) throws Exception{
//        //内存中添加两个用户,且密码是加密后的形式,这样Security才承认这个认证(强制加密密码)
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("kehan").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3");
//    }

//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser(userBuilder.username("user").password("password").roles("User"))
//                .
//    }
