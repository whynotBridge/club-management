package com.clubmanagement.service.impl;

import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.MySession;
import com.clubmanagement.commom.Result;
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

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    ClubApplicationMapper clubApplicationMapper;

    @Autowired
    ClubMapper clubMapper;

    /**
     * 登入
     * @param loginDTO
     * @return
     */
    public Admin login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();


        return adminMapper.login(username, password);

    }

    public Admin selectById(int adminId){
        return adminMapper.selectById(adminId);
    }

    /**
     * 管理员获取社团注册
     */
    public List<ClubApplication> getAllApplyClubApplications(){
//        //获取当前登录用户的id
//        int adminId=Context.getCurrentSession().getId();
//
//        //根据id获取用户信息
//        Admin admin=adminMapper.selectById(adminId);
//
//        //判断用户是否为管理员
//        if(admin==null){
////            return Result.fail("您不是管理员，无法审核社团注册");
//            return null;
//        }
//        这段没必要了，因为要先登入才会进行相关管理员操作

        //获取所有未审核的社团
        List<ClubApplication> clubApplications = clubApplicationMapper.getAllApplyClubApplications(ApplyStatusEnum.apply);
        return clubApplications;
    }

    /**
     * 管理员同意社团申请
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
    }

    /**
     * 管理员拒绝社团申请
     * @param clubApplicationId
     */
    public void reject(int clubApplicationId){
        //根据社团申请id获取社团申请
        ClubApplication clubApplication = clubApplicationMapper.selectById(clubApplicationId);

        Club club=new Club();
        BeanUtils.copyProperties(clubApplication, club);

        //拒绝申请
        clubApplication.setStatus(ApplyStatusEnum.reject);
        clubApplicationMapper.updateById(clubApplication);//更新申请表中的状态
    }
}
