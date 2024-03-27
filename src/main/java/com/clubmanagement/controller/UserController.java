package com.clubmanagement.controller;

import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.Result;
import com.clubmanagement.commom.MySession;
import com.clubmanagement.mapper.ClubApplicationMapper;
import com.clubmanagement.model.Enums.ApplyStatusEnum;
import com.clubmanagement.model.Enums.UserEnum;
import com.clubmanagement.model.dtos.UserLoginDTO;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.model.pojos.ClubApplication;
import com.clubmanagement.service.ClubService;
import com.clubmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.clubmanagement.model.pojos.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 用户登入
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ClubApplicationMapper clubApplicationMapper;

    @Autowired
    ClubService clubService;

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<User> login(HttpServletRequest request,@RequestBody UserLoginDTO userLoginDTO) {
        log.info("员工登录：{}", userLoginDTO);

        User user= userService.login(userLoginDTO);

        if(user == null){
            return Result.fail("登录失败");
        }

        MySession mySession = MySession.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .role(user.getRole())
                .build();
        //登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("user",mySession);

        return Result.success(user);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出")
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


        //判断用户是否为管理员
        if(mySession.getRole() != UserEnum.Admin){
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
        //根据社团申请id获取社团申请
        ClubApplication clubApplication = clubApplicationMapper.selectById(clubApplicationId);

        Club club=new Club();
        BeanUtils.copyProperties(clubApplication, club);


        //同意申请
        clubApplication.setStatus(ApplyStatusEnum.agree);
        clubApplicationMapper.updateById(clubApplication);//先更新申请表中的状态

        //插入社团表
        clubService.insert(club);

        return Result.success("审核成功");
    }
}
