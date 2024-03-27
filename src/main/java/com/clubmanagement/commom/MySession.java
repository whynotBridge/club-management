package com.clubmanagement.commom;

import com.clubmanagement.model.Enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MySession {
    private int userId;
    private String userName;

    @Enumerated(EnumType.STRING)
    private UserEnum role;
}
