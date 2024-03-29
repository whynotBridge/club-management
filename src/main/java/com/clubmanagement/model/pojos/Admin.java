package com.clubmanagement.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private int AdminId;

    private String username;

    private String password;

    private String email;


    //pattern = "yyyy-MM-dd HH:mm:ss"
    private LocalDateTime createDate;

}
