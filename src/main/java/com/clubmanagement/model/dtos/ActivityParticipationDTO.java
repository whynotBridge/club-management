package com.clubmanagement.model.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel("活动参与返回信息")
public class ActivityParticipationDTO implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private int userId;

    @ApiModelProperty(value = "报名参与表ID")
    private int participationId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "缴费情况")
    private boolean isPaid;

    @ApiModelProperty(value = "签到情况")
    private boolean isSigned;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
