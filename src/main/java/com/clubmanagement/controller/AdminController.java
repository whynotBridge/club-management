package com.clubmanagement.controller;

import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.MySession;
import com.clubmanagement.commom.Result;
import com.clubmanagement.mapper.ClubApplicationMapper;
import com.clubmanagement.model.dtos.QueryClubApplication;
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
import java.io.IOException;
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

    @GetMapping("/allNewClub")
    @ApiOperation("管理员审核社团注册")
    public Result<?> newClub(){
        List<QueryClubApplication> clubApplications=adminService.getAllApplyClubApplications();
        if(clubApplications.isEmpty())
            return Result.success("没有待审核的社团注册");
        return Result.success(clubApplications);
    }

    @PostMapping("/agree/{clubApplicationId}")
    @ApiOperation("管理员同意社团注册")
    @Transactional
    public Result<?> agree(@PathVariable int clubApplicationId){
        adminService.agree(clubApplicationId);
        return Result.success("审核成功");
    }

    @PostMapping("/reject/{clubApplicationId}")
    @ApiOperation("管理员拒绝社团注册")
    @Transactional
    public Result<?> reject(@PathVariable int clubApplicationId){
        adminService.reject(clubApplicationId);
        return Result.success("审核成功");
    }


    @GetMapping("/backup")
    @ApiOperation("管理员备份数据库")
    public Result<?> backup() {
        try {
            log.info("数据开始备份了。。。");
            adminService.backupDatabase();
            return Result.success("备份成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("备份过程异常！");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/restore")
    @ApiOperation("管理员还原数据库")
    public Result<?> restore(){
        try {
            log.info("数据开始还原了。。。");
            adminService.restoreDatabase();
            return Result.success("数据还原成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("还原过程异常！");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
