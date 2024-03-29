package com.clubmanagement.model.pojos;

import com.clubmanagement.model.enums.ApplyStatusEnum;
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
public class ClubApplication implements Serializable {
    private static final long serialVersionUID = 1L;

    private int applicationId;
    private int presidentId;
    private String clubName;
    private String description;
    private String contactInfo;
    private String activitySpace;

    @Enumerated(EnumType.STRING)
    private ApplyStatusEnum status;

}
