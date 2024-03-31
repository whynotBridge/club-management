package com.clubmanagement.controller;

import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.Result;
import com.clubmanagement.mapper.ActivityParticipationMapper;
import com.clubmanagement.mapper.ClubMapper;
import com.clubmanagement.model.dtos.ActivityParticipationDTO;
import com.clubmanagement.model.dtos.PublishActivityDTO;
import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.model.pojos.ActivityParticipation;
import com.clubmanagement.service.ActivityService;
import com.clubmanagement.service.FeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
@Api(tags = "活动管理")
public class ActivityController {
    @Autowired
    ClubMapper clubMapper;

    @Autowired
    ActivityParticipationMapper activityParticipationMapper;

    @Autowired
    FeeService feeService;

    @Autowired
    ActivityService activityService;

    @PostMapping
    @ApiOperation("社长发布活动")
    public Result<?> addActivity(@RequestBody PublishActivityDTO publishActivityDTO){

        activityService.addActivity(publishActivityDTO);
        return Result.success("发布活动成功");
    }

    @GetMapping("/{clubId}")
    @ApiOperation("根据社团id获取社团活动")
    public Result<?> getActivitiesByClubId(@PathVariable int clubId){
        List<Activity> activities=activityService.getActivitiesByClubId(clubId);
        if(activities==null || activities.size()==0)
            return Result.fail("你的社团没有组织任何活动");

        return Result.success(activities);
    }


    @PostMapping("/join/{activityId}")
    @ApiOperation("用户报名参加活动")
    @Transactional
    public Result<?> joinActivity(@PathVariable int activityId){
        return Result.success(activityService.joinActivity(activityId));
    }

    @GetMapping("/getMyParticipation/{clubId}")
    @ApiOperation("获取我参与的活动")
    public Result<List<ActivityParticipationDTO>> getMyParticipation(@PathVariable int clubId){
        List<ActivityParticipationDTO> res=activityService.getMyParticipation(clubId);
        if(res==null)
            return Result.fail("您暂无参加的活动！");

        return Result.success(res);
    }


    @PostMapping("/pay/{activityId}")
    @ApiOperation("用户缴费")
    public Result<String> payFee(@PathVariable int activityId){
        return Result.success(feeService.payFee(activityId));
    }

    @PostMapping("/sign/{activityId}")
    @ApiOperation("用户签到")
    public Result<String> sign(@PathVariable int activityId){
        //根据session获得userId
        int userId=Context.getCurrentSession().getId();

        //懒得加测试数据，先注释掉
//        //先根据id获取Activity信息
//        Activity activity=activityService.getActivityById(activityId);
//        //判断时间是否在签到时间内
//        Date now=new Date(System.currentTimeMillis());
//        if(now.before(activity.getStartTime())||now.after(activity.getEndTime()))
//            return Result.fail("未在签到时间内");

        //根据活动id和用户id获取参加活动信息
        ActivityParticipation activityParticipation=activityParticipationMapper.getByAIdAndUId(activityId,userId);
        //判断是否已经签到
        if(activityParticipation.isSigned())
            return Result.success("您已经签到过了");

        //更新签到状态
        activityParticipationMapper.Sign(activityId,userId);
        return Result.success("签到成功");
    }

    @GetMapping("/getParticipation/{activityId}")
    @ApiOperation("根据活动id获取活动参与信息")
    public Result<?> getParticipationById(@PathVariable int activityId){

        //根据活动id获取活动参与表信息
        List<ActivityParticipation> activityParticipations=activityParticipationMapper.getByAId(activityId);
        if(activityParticipations==null || activityParticipations.size()==0)
            return Result.fail("没有人参加该活动");

        return Result.success(activityParticipations);

    }

    //根据活动id获取所有未签到的成员信息
    @GetMapping("/getUnSigned/{activityId}")
    @ApiOperation("根据活动id获取所有未签到的成员信息")
    public Result<?> getUnSigned(@PathVariable int activityId){
        //根据活动id获取所有未签到的成员信息
        List<ActivityParticipation> activityParticipations=activityParticipationMapper.getAllUnSignedByAId(activityId);
        if(activityParticipations==null || activityParticipations.size()==0)
            return Result.fail("所有人都已经签到！");

        return Result.success(activityParticipations);
    }

    //社长根据活动参与id批量签到
    @PostMapping("/signBatch")
    @ApiOperation("社长根据活动参与id批量签到")
    @Transactional
    public Result<?> signBatch(@RequestBody List<Integer> apids){
        for(int id:apids){
            activityParticipationMapper.SignById(id);
        }
        return Result.success("批量签到成功");
    }

}
