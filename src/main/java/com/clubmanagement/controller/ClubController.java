package com.clubmanagement.controller;

import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.Result;

import com.clubmanagement.mapper.ClubApplicationMapper;
import com.clubmanagement.model.dtos.QueryMyclubDTO;
import com.clubmanagement.model.enums.ApplyStatusEnum;
import com.clubmanagement.model.dtos.ClubApplicationDTO;
import com.clubmanagement.model.dtos.UpdateClubDTO;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.model.pojos.ClubApplication;
import com.clubmanagement.service.ClubService;
import com.clubmanagement.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "社团管理")
@RestController
@RequestMapping("/clubs")
@Slf4j
public class ClubController {
    @Autowired
    ClubService clubService;

    @Autowired
    MemberService memberService;

    @Autowired
    ClubApplicationMapper clubApplicationMapper;


    @GetMapping
    @ApiOperation("获取所有未申请加入社团信息")
    public Result<List<Club>> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();

        if(clubs == null || clubs.size() == 0)
                return Result.fail("没有社团信息");

            return Result.success(clubs);
    }

    //社长可以修改社团信息，包括社团简介、联系方式、活动场地等
    @PutMapping("/{clubId}")
    @ApiOperation("社长修改社团信息")
    public Result<?> updateClub(@PathVariable int clubId,@RequestBody UpdateClubDTO updateClubDTO) {
        log.info("updateClubDTO,{}",updateClubDTO);

        //更改后参数以及穿了cid
//        //先根据session中的userId（社长）获取社团
//        int userId = Context.getCurrentSession().getId();
//        log.info("当前登录用户的id：{}", userId);
//        //根据社长id获得社团id
//        int clubId=clubService.selectClubIdByPId(userId);
//        log.info("clubId,{}",clubId);

        Club club=Club.builder().clubId(clubId)
                .description(updateClubDTO.getDescription())
                .contactInfo(updateClubDTO.getContactInfo())
                .activitySpace(updateClubDTO.getActivitySpace())
                .build();

        clubService.updateClub(club);
        return Result.success("修改成功");
    }

    @GetMapping("/myclub")
    @ApiOperation("获取当前登录用户的社团信息")
    public Result<List<QueryMyclubDTO>> getMyClub() {
        List<QueryMyclubDTO> myclubsDTO=clubService.getMyclub();
        if(myclubsDTO==null)
            return Result.fail("您暂无加入任何社团");
        return Result.success(myclubsDTO);
    }

    @PostMapping
    @ApiOperation("社长申请创建社团")
    public Result<?> CreateClub(@RequestBody ClubApplicationDTO clubApplicationDTO) {
        ClubApplication clubApplication = new ClubApplication();
        BeanUtils.copyProperties(clubApplicationDTO, clubApplication);
        //根据session中的userId（社长）
        int userId = Context.getCurrentSession().getId();
        clubApplication.setPresidentId(userId);
        clubApplication.setStatus(ApplyStatusEnum.apply);

        clubApplicationMapper.insert(clubApplication);
        return Result.success("申请创建社团成功");
    }

}
