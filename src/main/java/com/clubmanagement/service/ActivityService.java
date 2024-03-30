package com.clubmanagement.service;

import com.clubmanagement.model.dtos.ActivityParticipationDTO;
import com.clubmanagement.model.pojos.Activity;

import java.util.List;

public interface ActivityService {
    void addActivity(Activity activity);

    List<Activity> getActivitiesByClubId(int clubId);

    double getAmountByActivityId(int activityId);

    Activity getActivityById(int activityId);

    /**
     * 根据社团id获取登入用户参加该社团活动的信息
     * @param clubId
     * @return
     */
    List<ActivityParticipationDTO> getMyParticipation(int clubId);
}
