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
public class Fee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int feeId;
    private int activityId;
    private int userId;
    private double amount;
    private boolean isPaid;
}
