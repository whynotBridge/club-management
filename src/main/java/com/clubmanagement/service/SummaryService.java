package com.clubmanagement.service;



public interface SummaryService {

    void save(int activityId, String info);

    String getSummaryByActivityId(int activityId);

    void update(int activityId, String info);
}
