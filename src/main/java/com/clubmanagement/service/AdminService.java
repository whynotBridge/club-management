package com.clubmanagement.service;

import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.Admin;

public interface AdminService {
    Admin login(LoginDTO loginDTO);

    Admin selectById(int adminId);
}
