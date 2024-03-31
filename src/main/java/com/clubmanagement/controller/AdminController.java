package com.clubmanagement.controller;

import com.clubmanagement.commom.MySession;
import com.clubmanagement.commom.Result;
import com.clubmanagement.mapper.ClubApplicationMapper;
import com.clubmanagement.model.enums.ApplyStatusEnum;
import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.model.pojos.Admin;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.model.pojos.ClubApplication;
import com.clubmanagement.service.AdminService;
import com.clubmanagement.service.ClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员管理")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    ClubApplicationMapper clubApplicationMapper;

    @Autowired
    ClubService clubService;

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public Result<Admin> login(HttpServletRequest request, @RequestBody @Validated LoginDTO loginDTO) {
        log.info("管理员登录：{}", loginDTO);

        Admin admin=adminService.login(loginDTO);

        if(admin == null){
            return Result.fail("用户名或密码错误！");
        }

        MySession mySession = MySession.builder()
                .id(admin.getAdminId())
                .userName(admin.getUsername())
                .build();
        //登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("user",mySession);

        return Result.success(admin);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("管理员退出")
    public Result<String> logout(HttpServletRequest request){
        //清理session
        request.getSession().removeAttribute("user");
        return Result.success("退出成功");
    }

    @PostMapping("/allNewClub")
    @ApiOperation("管理员审核社团注册")
    public Result<?> newClub(HttpServletRequest request){
        //获取当前登录用户的id
        MySession mySession = (MySession) request.getSession().getAttribute("user");

        //根据id获取用户信息
        Admin admin=adminService.selectById(mySession.getId());

        //判断用户是否为管理员
        if(admin==null){
            return Result.fail("您不是管理员，无法审核社团注册");
        }

        //获取所有未审核的社团
        List<ClubApplication> clubApplications = clubApplicationMapper.getAllApplyClubApplications(ApplyStatusEnum.apply);
        if(clubApplications == null || clubApplications.size() == 0){
            return Result.fail("没有未审核的社团");
        }

        return Result.success(clubApplications);
    }

    @PostMapping("/agree/{clubApplicationId}")
    @ApiOperation("管理员同意社团注册")
    @Transactional
    public Result<?> agree(@PathVariable int clubApplicationId){
        adminService.agree(clubApplicationId);
        return Result.success("审核成功");
    }
}
