package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.UserMapper;
import com.clubmanagement.model.dtos.UserLoginDTO;
import com.clubmanagement.model.pojos.User;
import com.clubmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();


        return userMapper.login(username, password);

    }
}
