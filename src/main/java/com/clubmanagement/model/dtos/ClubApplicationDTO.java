package com.clubmanagement.model.dtos;

import com.clubmanagement.model.Enums.ApplyStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
public class ClubApplicationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String clubName;
    private String description;
    private String contactInfo;
    private String activitySpace;

}
