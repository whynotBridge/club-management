package com.clubmanagement.service;


import com.clubmanagement.model.pojos.Fee;

public interface FeeService {

    /**
     * 根据用户id和活动id缴费
     * @param activityId
     */
    String payFee(int activityId);

    /**
     * 根据用户id和活动id查询缴费信息
     * @param activityId
     * @param userId
     * @return
     */
    Fee getByAIdAndUId(int activityId, int userId);
}
