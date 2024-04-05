package com.clubmanagement.model.dtos;

import com.clubmanagement.model.enums.ApplyStatusEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("社团注册响应")
public class QueryClubApplication implements Serializable {
    private static final long serialVersionUID = 1L;

    private int applicationId;
    private String presidentName;
    private String clubName;
    private String description;
    private String contactInfo;
    private String activitySpace;

    @Enumerated(EnumType.STRING)
    private ApplyStatusEnum status;

}
