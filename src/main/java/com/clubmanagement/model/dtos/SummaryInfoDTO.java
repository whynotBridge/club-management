package com.clubmanagement.model.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 这个类有点多余，但不实现Serializable，在参数只用String不能json格式解析
 */
@Data
@ApiModel("活动总结信息传参")
public class SummaryInfoDTO implements Serializable {

    @ApiModelProperty("总结信息")
    @NotEmpty
    private String info;
}
