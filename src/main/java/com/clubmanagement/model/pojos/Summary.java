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
public class Summary implements Serializable {

    private static final long serialVersionUID = 1L;

    private int summaryId;
    private int activityId;
    private String info;
}
