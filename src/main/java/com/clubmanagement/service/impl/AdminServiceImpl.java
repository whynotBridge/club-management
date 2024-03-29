package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.AdminMapper;
import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.Admin;
import com.clubmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    public Admin login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();


        return adminMapper.login(username, password);

    }

    public Admin selectById(int adminId){
        return adminMapper.selectById(adminId);
    }
}
