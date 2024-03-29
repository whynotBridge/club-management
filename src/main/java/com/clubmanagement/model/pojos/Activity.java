package com.clubmanagement.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {
    final private static long serialVersionUID = 1L;

    private int activityId;
    private int clubId;
    private String theme;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private double amount;

}
