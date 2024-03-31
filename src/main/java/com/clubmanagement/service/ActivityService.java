package com.clubmanagement.service;

import com.clubmanagement.model.dtos.MyActivityParticipationDTO;
import com.clubmanagement.model.dtos.PublishActivityDTO;
import com.clubmanagement.model.pojos.Activity;

import java.util.List;

public interface ActivityService {

    /**
     * 社长发布活动
     * @param publishActivityDTO
     */
    void addActivity(PublishActivityDTO publishActivityDTO);

    List<Activity> getActivitiesByClubId(int clubId);

    double getAmountByActivityId(int activityId);

    Activity getActivityById(int activityId);

    /**
     * 根据社团id获取登入用户参加该社团活动的信息
     * @param clubId
     * @return
     */
    List<MyActivityParticipationDTO> getMyParticipation(int clubId);

    /**
     * 用户报名参加活动
     * @param activityId
     */
    String joinActivity(int activityId);
}
