package com.springbootdemo.controller.异常测试;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 自定义异常继承RntimeEXCEPTION
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class 自定义异常 extends RuntimeException {
        private String code;//状态码
        private String msg;//信息

}
