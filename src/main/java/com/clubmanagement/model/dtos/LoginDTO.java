package com.clubmanagement.model.dtos;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ApiModel(value = "登录参数")
public class LoginDTO implements Serializable {

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
