package com.clubmanagement.model.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ApiModel("我的活动参与返回信息")
public class MyActivityParticipationDTO implements Serializable {
    @ApiModelProperty(value = "活动ID")
    private int activityId;

    @ApiModelProperty(value = "活动主题")
    private String theme;

    @ApiModelProperty(value = "活动描述")
    private String description;

    @ApiModelProperty(value = "活动开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "活动结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "活动地点")
    private String location;

    @ApiModelProperty(value = "是否签到")
    private boolean isSigned;

    @ApiModelProperty(value = "缴费情况")
    private boolean isPaid;

    @ApiModelProperty(value = "活动费用")
    private double amount;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
