package com.clubmanagement.model.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("发布社团活动传参")
public class PublishActivityDTO implements Serializable {

    @NotEmpty
    @ApiModelProperty("活动主题")
    private String theme;

    @NotEmpty
    @ApiModelProperty("活动描述")
    private String description;

    @NotEmpty
    @ApiModelProperty("活动场地")
    private String location;

    @NotEmpty
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @NotEmpty
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @NotEmpty
    @ApiModelProperty("报名缴费")
    private double amount;

}
