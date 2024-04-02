package com.clubmanagement.service;

import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.User;

public interface UserService {

    /**
     * 根据用户名和密码查询信息
     * @param loginDTO
     * @return
     */
    User login(LoginDTO loginDTO);


    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    User getById(int userId);
}
