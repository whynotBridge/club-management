package com.clubmanagement.model.pojos;

import com.clubmanagement.model.Enums.PositionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private int memberId;
    private int userId;
    private int clubId;

    @Enumerated(EnumType.STRING)
    private PositionEnum position;

    private LocalDateTime joinDate;
}
