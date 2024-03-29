package com.clubmanagement.model.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClubApplicationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String clubName;
    private String description;
    private String contactInfo;
    private String activitySpace;

}
