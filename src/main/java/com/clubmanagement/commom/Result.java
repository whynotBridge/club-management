package com.clubmanagement.commom;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
@ApiModel("返回结果")
public class Result<T> implements Serializable{

    @ApiModelProperty("编码：1成功，0和其它数字为失败")
    private Integer code;

    @ApiModelProperty("提示信息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;


    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> Result<T> successMsg(String msg,T data) {
        Result<T> r = new Result<T>();
        r.msg=msg;
        r.code = 1;
        r.data=data;
        return r;
    }

    public static <T> Result<T> fail(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 0;
        return r;
    }

}
