package com.clubmanagement.model.pojos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Club implements Serializable {
    private static final long serialVersionUID = 1L;

    private int clubId;

    private String clubName;
    private String description;
    private String contactInfo;
    private String activitySpace;
    private int presidentId;

}

