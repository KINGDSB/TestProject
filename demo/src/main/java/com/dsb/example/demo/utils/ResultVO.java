package com.dsb.example.demo.utils;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by Coder.One
 * 2018/4/30 13:19
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private  String message;

    /**
     * 具体内容
     */
    private T data;
}
