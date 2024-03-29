package com.clubmanagement.model.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("修改社团信息参数")
public class UpdateClubDTO implements Serializable {
    @ApiModelProperty(value = "社团描述")
    private String description;
    @ApiModelProperty(value = "联系方式")
    private String contactInfo;
    @ApiModelProperty(value = "活动场地")
    private String activitySpace;
}
