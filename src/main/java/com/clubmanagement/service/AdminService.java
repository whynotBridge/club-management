package com.clubmanagement.service;

import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.Admin;

public interface AdminService {
    /**
     * 管理员登入
     * @param loginDTO
     * @return
     */
    Admin login(LoginDTO loginDTO);


    /**
     * 根据id查询管理员信息
     * @param adminId
     * @return
     */
    Admin selectById(int adminId);

    /**
     * 管理员审核社团申请
     * @param clubApplicationId
     */
    void agree(int clubApplicationId);
}
