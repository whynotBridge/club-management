package com.clubmanagement.service;


import com.clubmanagement.model.enums.PayStatusEnum;

public interface FeeService {

    void addFee(int activityId, int userId, double amount, PayStatusEnum payStatusEnum);

    /**
     * 根据用户id和活动id缴费
     * @param activityId
     */
    String payFee(int activityId);
}
