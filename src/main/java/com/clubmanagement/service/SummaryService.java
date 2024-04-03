package com.clubmanagement.service;


import com.clubmanagement.model.dtos.SummaryInfoDTO;

public interface SummaryService {

    /**
     * 社长发布活动总结
     * @param activityId
     * @param summaryInfo
     */
    String publishSummary(int activityId, SummaryInfoDTO summaryInfo);

    String getSummaryByActivityId(int activityId);

    void update(int activityId, SummaryInfoDTO summaryInfo);
}
