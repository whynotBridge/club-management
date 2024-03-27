package com.clubmanagement.service;

import com.clubmanagement.model.pojos.Activity;

import java.util.List;

public interface ActivityService {
    void addActivity(Activity activity);

    List<Activity> getActivitiesByClubId(int clubId);

    double getAmountByActivityId(int activityId);

    Activity getActivityById(int activityId);

}
