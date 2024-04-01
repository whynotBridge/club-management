package com.clubmanagement.controller;
//社长端
//        成员管理: 社长可以审核成员申请、查看成员信息、管理成员权限等。


import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.Result;
import com.clubmanagement.model.dtos.MemberDetailDTO;
import com.clubmanagement.model.enums.PositionEnum;
import com.clubmanagement.model.pojos.Member;
import com.clubmanagement.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/members")
@Api(tags = "成员管理")
public class MemberController {

    @Autowired
    MemberService memberService;


    @PostMapping("/join/{clubId}")
    @ApiOperation("用户加入社团")
    public Result<String> joinClub(@PathVariable int clubId) {

        return Result.success(memberService.joinClub(clubId));

    }

    @PostMapping("/quit/{clubId}")
    @ApiOperation("用户退出社团")
    public Result quitClub(@PathVariable int clubId) {
        //todo 感觉退出的话，还要涉及fee表，活动表，活动参与表
        return Result.success(memberService.quitClub(clubId));
    }

    @GetMapping("/{clubId}")
    @ApiOperation("社长获取成员信息")
    public Result<List<MemberDetailDTO>> getByClubId(@PathVariable int clubId) {
        //更新后直接传id
//        //根据session中的userId获取clubId
//        int userId=Context.getCurrentSession().getId();
//        int clubId = memberService.getOnlyClubIdByUId(userId, PositionEnum.president);

        List<MemberDetailDTO> members = memberService.getByClubId(clubId);
        return Result.success(members);
    }


    @PutMapping("/position/{memberId}")
    @ApiOperation("社长修改成员职位")
    public Result<?> updatePosition(@PathVariable int memberId, @RequestParam PositionEnum position) {
        memberService.updatePosition(memberId,position);
        return Result.success("修改成功");
    }


}
