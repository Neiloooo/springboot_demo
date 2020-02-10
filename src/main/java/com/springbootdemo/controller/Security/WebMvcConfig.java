package com.springbootdemo.controller.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 对于MVC的设置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * Spring MVC ViewControllerRegistry快捷配置支持如下几种需求(代替Controller进行url与业务的关联) :
     *     针对某些URL请求只需要返回某个HTTP响应码即可
     *     针对某些URL请求只需要跳转到另外一个URL
     *     针对某些URL请求只需要渲染和展示指定视图而无需业务逻辑
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

}
