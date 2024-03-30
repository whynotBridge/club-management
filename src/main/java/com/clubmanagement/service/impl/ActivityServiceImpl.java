package com.clubmanagement.service.impl;

import com.clubmanagement.commom.Context;
import com.clubmanagement.commom.Result;
import com.clubmanagement.mapper.ActivityMapper;
import com.clubmanagement.mapper.ActivityParticipationMapper;
import com.clubmanagement.mapper.ClubMapper;
import com.clubmanagement.mapper.SummaryMapper;
import com.clubmanagement.model.dtos.ActivityParticipationDTO;
import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.model.pojos.ActivityParticipation;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.model.pojos.Summary;
import com.clubmanagement.service.ActivityService;
import com.clubmanagement.service.ClubService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ActivityParticipationMapper activityParticipationMapper;

    public void addActivity(Activity activity){
        activityMapper.addActivity(activity);
    }

    public List<Activity> getActivitiesByClubId(int clubId){
        return activityMapper.getActivitiesByClubId(clubId);
    }

    public double getAmountByActivityId(int activityId){
        return activityMapper.getAmountByActivityId(activityId);
    }

    public Activity getActivityById(int activityId){
        return activityMapper.getById(activityId);
    }

    /**
     * 根据社团id获取登入用户参加该社团活动的信息
     * @param clubId
     * @return
     */
    public List<ActivityParticipationDTO> getMyParticipation(int clubId){
        //根据session获得userId
        int userId= Context.getCurrentSession().getId();

        //根据用户id查询其报名参加的所有活动
        List<ActivityParticipation> activityParticipations=activityParticipationMapper.getParticipationByUId(userId);
        if(activityParticipations==null || activityParticipations.size()==0)
            return null;

        List<ActivityParticipationDTO> res=new ArrayList<>();
        //拼接完整活动信息
        for(ActivityParticipation ap:activityParticipations){

            //1.根据活动id获取活动信息
            Activity activity=activityMapper.getById(ap.getActivityId());

            //2.后面增加需求，加入参数clubId，这里先这样处理
            if(activity.getClubId()!=clubId)
                continue;

            //3如果满足参数条件

            //3.1先传活动报名信息
            ActivityParticipationDTO activityParticipationDTO=ActivityParticipationDTO.builder()
                    .userId(ap.getUserId())
                    .userName(ap.getUserName())
                    .isSigned(ap.isSigned())
                    .build();

            //3.1再传活动信息
            BeanUtils.copyProperties(activity,activityParticipationDTO);
            res.add(activityParticipationDTO);
        }

        return res;
    }

}
