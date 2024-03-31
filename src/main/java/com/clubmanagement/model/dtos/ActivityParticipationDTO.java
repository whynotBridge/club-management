package com.clubmanagement.model.dtos;

import com.clubmanagement.model.enums.PayStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@ApiModel("活动参与返回信息")
public class ActivityParticipationDTO implements Serializable {
//    @ApiModelProperty(value = "用户ID")
//    private int userId;
//
//    @ApiModelProperty(value = "用户名")
//    private String userName;
//
//    @ApiModelProperty(value = "社团ID")
//    private int clubId;
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
    @Enumerated(EnumType.STRING)
    private PayStatusEnum status;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
