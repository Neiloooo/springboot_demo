package com.springbootdemo.controller.配置文件的注入;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data //lombook注解
@Component //让spring容器把这个实体类放入的标识
@PropertySource("classpath:resource.properties")
@ConfigurationProperties("test") //配置文件属性的前缀,会自动根据属性名寻找对应的配置文件中的值,为其注入
public class 实体类映射配置文件 {

//    @Value("${domain}")
    private String name;
//    @Value("${name}")
    private String domain;
}
