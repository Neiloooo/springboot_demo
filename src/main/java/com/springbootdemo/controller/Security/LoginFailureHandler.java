package com.springbootdemo.controller.Security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 创建登陆失败处理器(覆盖原有的登陆失败处理方案)
 * 原有的是执行重定向或转发到defaultfailureurl(如果设置)，Otherw返回401错误代码
 */
@Slf4j
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /* 默认：执行重定向或转发到defaultfailureurl(如果设置)，Otherw返回401错误代码 */
    //super.onAuthenticationFailure(request,response,exception)
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException{
        logger.error("登陆失败");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //现在都是默认定死了的UTF-8
        PrintWriter writer = response.getWriter(); //还是通过字符流的方式写进去信息,然后传回去
        writer.write(exception.getMessage());
        writer.flush();
        writer.close();
    }
}
