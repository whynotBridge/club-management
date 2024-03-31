package com.clubmanagement.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityParticipation implements Serializable {
    private static long serialVersionUID = 1L;

    private int participationId;
    private int activityId;
    private int userId;
    private boolean isSigned;

}
