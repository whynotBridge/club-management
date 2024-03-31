package com.clubmanagement.service.impl;

import com.clubmanagement.commom.Context;
import com.clubmanagement.mapper.FeeMapper;
import com.clubmanagement.model.enums.PayStatusEnum;
import com.clubmanagement.model.pojos.Fee;
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

    /**
     * 根据用户id和活动id缴费
     * @param activityId
     */
    public String payFee(int activityId){
        //根据session获得userId
        int userId= Context.getCurrentSession().getId();

        //根据用户id和活动id查询缴费单
        Fee fee=feeMapper.getByAIdAndUId(activityId,userId);
        if(fee.getStatus().equals(PayStatusEnum.Paid))
            return "您已经进行缴费了！，请勿重复缴费！" ;

        //根据用户id和活动id更新缴费状态
        feeMapper.payFee(activityId,userId,PayStatusEnum.Paid);
        return "缴费成功！";
    }
}
