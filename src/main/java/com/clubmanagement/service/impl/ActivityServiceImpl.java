package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.ActivityMapper;
import com.clubmanagement.mapper.ClubMapper;
import com.clubmanagement.mapper.SummaryMapper;
import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.model.pojos.Summary;
import com.clubmanagement.service.ActivityService;
import com.clubmanagement.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

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
        return activityMapper.getByid(activityId);
    }

}
