package com.clubmanagement.service;

import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.User;

public interface UserService {
    User login(LoginDTO loginDTO);
}
