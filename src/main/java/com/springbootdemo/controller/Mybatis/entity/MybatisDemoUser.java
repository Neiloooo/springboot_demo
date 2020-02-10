package com.springbootdemo.controller.Mybatis.entity;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MybatisDemoUser {
    private Integer id;
    private String name;
    private String pwd;
}
