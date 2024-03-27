package com.clubmanagement.model.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateClubDTO implements Serializable {
    private String description;
    private String contactInfo;
    private String activitySpace;
}
