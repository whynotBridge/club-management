package com.clubmanagement.model.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("社团申请参数")
public class ClubApplicationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("社团名称")
    private String clubName;

    @ApiModelProperty("简介描述")
    private String description;

    @ApiModelProperty("联系方式")
    private String contactInfo;

    @ApiModelProperty("活动场地")
    private String activitySpace;

}
