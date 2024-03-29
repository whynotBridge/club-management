package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.UserMapper;
import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.User;
import com.clubmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public User login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();


        return userMapper.login(username, password);

    }
}
