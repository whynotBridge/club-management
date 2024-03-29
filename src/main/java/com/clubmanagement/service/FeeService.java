package com.clubmanagement.service;


import com.clubmanagement.model.enums.PayStatusEnum;

public interface FeeService {

    void addFee(int activityId, int userId, double amount, PayStatusEnum payStatusEnum);

    void PayFee(int activityId, int userId, PayStatusEnum payStatusEnum);
}
