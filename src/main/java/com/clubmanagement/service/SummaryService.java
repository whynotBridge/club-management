package com.clubmanagement.service;



public interface SummaryService {

    /**
     * 社长发布活动总结
     * @param activityId
     * @param info
     */
    String publishSummary(int activityId, String info);

    String getSummaryByActivityId(int activityId);

    void update(int activityId, String info);
}
