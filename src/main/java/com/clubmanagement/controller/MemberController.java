package com.clubmanagement.controller;
//社长端
//        成员管理: 社长可以审核成员申请、查看成员信息、管理成员权限等。


import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.Result;
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

    @GetMapping("/{clubId}")
    @ApiOperation("社长获取成员信息")
    public Result<List<Member>> getByClubId(@PathVariable int clubId) {
        //更新后直接传id
//        //根据session中的userId获取clubId
//        int userId=Context.getCurrentSession().getId();
//        int clubId = memberService.getOnlyClubIdByUId(userId, PositionEnum.president);

        List<Member> members = memberService.getByClubId(clubId);
        return Result.success(members);
    }

    @PostMapping("/join/{clubId}")
    @ApiOperation("用户加入社团")
    public Result joinClub(@PathVariable int clubId) {
        //根据session中的userId获取用户id
        int userId = Context.getCurrentSession().getId();
        Member member=Member.builder().userId(userId).clubId(clubId)
                .position(PositionEnum.applyJoin)   //申请加入
                .joinDate(LocalDateTime.now())
                .build();

        memberService.joinClub(member);
        return Result.success("申请加入成功");
    }

    @PostMapping("/quit/{clubId}")
    @ApiOperation("用户退出社团")
    public Result quitClub(@PathVariable int clubId) {
//        Long userId = Context
        //根据session获得userId
        int userId = Context.getCurrentSession().getId();
        //根据userId获取memberId
        int memberId =memberService.getMemberIdByUserId(userId);

        Member member = Member.builder().memberId(memberId).position(PositionEnum.applyQuit).build();

        memberService.updatePosition(member);
        return Result.success("申请退出成功");
    }

    @PutMapping("/position/{memberId}")
    @ApiOperation("社长修改成员职位")
    public Result updatePosition(@PathVariable int memberId, @RequestParam PositionEnum position) {
        Member member = Member.builder().memberId(memberId).position(position).build();
        memberService.updatePosition(member);
        return Result.success("修改成功");
    }

    //定期删除已退出（position为alreadyQuit）的成员
    @DeleteMapping()
    @ApiOperation("删除已退出的成员")
    public Result deleteQuitMembers() {
        memberService.deleteQuitMembers();
        return Result.success("删除已退出的成员成功");
    }
}
