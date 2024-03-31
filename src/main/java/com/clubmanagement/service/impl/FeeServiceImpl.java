package com.clubmanagement.service.impl;

import com.clubmanagement.commom.Context;
import com.clubmanagement.mapper.FeeMapper;
import com.clubmanagement.model.pojos.Fee;
import com.clubmanagement.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    FeeMapper feeMapper;

    public void addFee(int activityId, int userId, double amount,boolean isPaid){
        feeMapper.addFee(activityId, userId, amount, isPaid);
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
        if(fee.isPaid())
            return "您已经进行缴费了！，请勿重复缴费！" ;
//        if(fee.getStatus().equals(PayStatusEnum.Paid))


        //根据用户id和活动id更新缴费状态
        feeMapper.payFee(activityId,userId,true);
        return "缴费成功！";
    }

    /**
     * 根据用户id和活动id查询缴费信息
     * @param activityId
     * @param userId
     * @return
     */
    public Fee getByAIdAndUId(int activityId, int userId){
        return feeMapper.getByAIdAndUId(activityId,userId);
    }
}
