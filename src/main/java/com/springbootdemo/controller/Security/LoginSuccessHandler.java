package com.springbootdemo.controller.Security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆成功处理器,登陆成功后返回前端一定信息?可是登陆后不是直接跳转正经的页面就行了吗?
 */
@Slf4j
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException{
//        /**
//         * 默认会帮我们跳转到上一次请求的页面上
//         */
//        super.onAuthenticationSuccess(request,response,authentication);
            log.info("开始加载登陆成功处理器");
            response.setStatus(HttpServletResponse.SC_OK); //返回200
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); //返回JSON_UTF_8格式
            //通过字符流返回前端登陆成功后的信息(自定制时候应该有用)
            PrintWriter writer = response.getWriter();
            writer.write("登陆成功");
            writer.flush();
            writer.close();
    }

}
