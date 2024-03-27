package com.clubmanagement.service;

import com.clubmanagement.model.dtos.UserLoginDTO;
import com.clubmanagement.model.pojos.User;

public interface UserService {
    User login(UserLoginDTO userLoginDTO);
}
