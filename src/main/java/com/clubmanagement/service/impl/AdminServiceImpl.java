package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.AdminMapper;
import com.clubmanagement.mapper.ClubApplicationMapper;
import com.clubmanagement.mapper.ClubMapper;
import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.enums.ApplyStatusEnum;
import com.clubmanagement.model.pojos.Admin;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.model.pojos.ClubApplication;
import com.clubmanagement.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    ClubApplicationMapper clubApplicationMapper;

    @Autowired
    ClubMapper clubMapper;

    public Admin login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();


        return adminMapper.login(username, password);

    }

    public Admin selectById(int adminId){
        return adminMapper.selectById(adminId);
    }

    /**
     * 管理员审核社团申请
     * @param clubApplicationId
     */
    @Transactional
    public void agree(int clubApplicationId){
        //根据社团申请id获取社团申请
        ClubApplication clubApplication = clubApplicationMapper.selectById(clubApplicationId);

        Club club=new Club();
        BeanUtils.copyProperties(clubApplication, club);


        //同意申请
        clubApplication.setStatus(ApplyStatusEnum.agree);
        clubApplicationMapper.updateById(clubApplication);//先更新申请表中的状态

        //插入社团表
        clubMapper.insert(club);
        //删除社团注册表的记录
        clubApplicationMapper.deleteById(clubApplicationId);
    }
}
