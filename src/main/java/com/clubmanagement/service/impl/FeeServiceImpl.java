package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.FeeMapper;
import com.clubmanagement.model.enums.PayStatusEnum;
import com.clubmanagement.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    FeeMapper feeMapper;

    public void addFee(int activityId, int userId, double amount, PayStatusEnum payStatusEnum){
        feeMapper.addFee(activityId, userId, amount, payStatusEnum);
    }

    public void PayFee(int activityId, int userId, PayStatusEnum payStatusEnum){
        feeMapper.PayFee(activityId, userId, payStatusEnum);
    }
}
