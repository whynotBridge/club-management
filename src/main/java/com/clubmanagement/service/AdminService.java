package com.clubmanagement.service;

import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.Admin;
import com.clubmanagement.model.pojos.ClubApplication;

import java.util.List;

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
     * 管理员同意社团申请
     * @param clubApplicationId
     */
    void agree(int clubApplicationId);

    /**
     * 管理员获取社团注册
     */
    List<ClubApplication> getAllApplyClubApplications();

    /**
     * 管理员拒绝社团申请
     * @param clubApplicationId
     */
    void reject(int clubApplicationId);
}
