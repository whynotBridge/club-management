package com.clubmanagement.model.pojos;

import com.clubmanagement.model.Enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserEnum role;

    //pattern = "yyyy-MM-dd HH:mm:ss"
    private LocalDateTime createDate;

}
