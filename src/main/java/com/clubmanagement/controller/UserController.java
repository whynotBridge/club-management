package com.clubmanagement.controller;

import com.clubmanagement.commom.Result;
import com.clubmanagement.commom.MySession;
import com.clubmanagement.mapper.ClubApplicationMapper;
import com.clubmanagement.model.dtos.LoginDTO;
import com.clubmanagement.service.ClubService;
import com.clubmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.clubmanagement.model.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<User> login(HttpServletRequest request,@RequestBody @Validated LoginDTO loginDTO) {
        log.info("用户登录：{}", loginDTO);

        User user= userService.login(loginDTO);

        if(user == null){
            return Result.fail("用户名或密码错误！");
        }

        MySession mySession = MySession.builder()
                .id(user.getUserId())
                .userName(user.getUsername())
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
    @ApiOperation("用户退出")
    public Result<String> logout(HttpServletRequest request){
        //清理session
        request.getSession().removeAttribute("user");
        return Result.success("退出成功");
    }

}
