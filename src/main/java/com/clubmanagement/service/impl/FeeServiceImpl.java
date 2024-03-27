package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.ActivityMapper;
import com.clubmanagement.mapper.FeeMapper;
import com.clubmanagement.model.Enums.PayStatusEnum;
import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.service.ActivityService;
import com.clubmanagement.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
