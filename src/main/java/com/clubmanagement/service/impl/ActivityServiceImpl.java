package com.clubmanagement.service.impl;

import com.clubmanagement.commom.Context;
import com.clubmanagement.mapper.*;
import com.clubmanagement.model.dtos.MyActivityParticipationDTO;
import com.clubmanagement.model.dtos.PublishActivityDTO;
import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.model.pojos.ActivityParticipation;
import com.clubmanagement.model.pojos.Fee;
import com.clubmanagement.model.pojos.User;
import com.clubmanagement.service.ActivityService;
import com.clubmanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ActivityParticipationMapper activityParticipationMapper;

    @Autowired
    FeeMapper feeMapper;

    @Autowired
    ClubMapper clubMapper;

    @Autowired
    UserService userService;


    /**
     * 社长发布活动
     * @param publishActivityDTO
     */
    public void addActivity(int clubId,PublishActivityDTO publishActivityDTO){
        //拷贝发布活动信息
        Activity activity=new Activity();
        BeanUtils.copyProperties(publishActivityDTO,activity);

//        //根据session获得userId
//        int userId= Context.getCurrentSession().getId();
//        //根据社长id获取社团id
//        int clubId=clubMapper.getIdByPId(userId);
        //设置活动所属社团
        activity.setClubId(clubId);
        activityMapper.addActivity(activity);
    }

    public List<Activity> getActivitiesByClubId(int clubId){
        return activityMapper.getActivitiesByClubId(clubId);
    }

    /**
     * 根据社团id获取登入用户参加该社团活动的信息
     * @param clubId
     * @return
     */
    public List<MyActivityParticipationDTO> getMyParticipation(int clubId){
        //根据session获得userId
        int userId= Context.getCurrentSession().getId();

        //根据用户id查询其报名参加的所有活动
        List<ActivityParticipation> activityParticipations=activityParticipationMapper.getParticipationByUId(userId);
        if(activityParticipations==null || activityParticipations.size()==0)
            return null;

        List<MyActivityParticipationDTO> res=new ArrayList<>();
        //拼接完整活动信息
        for(ActivityParticipation ap:activityParticipations){

            //1.根据活动id获取活动信息
            Activity activity=activityMapper.getById(ap.getActivityId());

            //2.后面增加需求，加入参数clubId，这里先这样处理
            if(activity.getClubId()!=clubId)
                continue;

            //3如果满足参数条件

            //3.1先传活动报名信息
            MyActivityParticipationDTO myActivityParticipationDTO = MyActivityParticipationDTO.builder()
                    .isSigned(ap.isSigned())
                    .activityId(ap.getActivityId())
                    .build();

            //3.2再传活动信息
            BeanUtils.copyProperties(activity, myActivityParticipationDTO);

            //3.3用户信息
            User user=userService.getById(userId);
            myActivityParticipationDTO.setEmail(user.getEmail());

            //3.4缴费信息
            Fee fee=feeMapper.getByAIdAndUId(activity.getActivityId(),userId);
            myActivityParticipationDTO.setPaid(fee.isPaid());
            myActivityParticipationDTO.setAmount(fee.getAmount());


            res.add(myActivityParticipationDTO);
        }

        return res;
    }

    /**
     * 用户报名参加活动
     * @param activityId
     */
    @Transactional
    public String joinActivity(int activityId){
        //1.根据session获得userId和username
        int userId=Context.getCurrentSession().getId();
        String userName=Context.getCurrentSession().getUserName();

        //2.先活动id和用户查询活动参与表是否已报名
        ActivityParticipation participation=activityParticipationMapper.getByAIdAndUId(activityId,userId);

        //3.如果已经存在
        if(participation!=null)
            return "您已经报名参加该活动了，请勿重复报名！";


        //4.如果不存在

        //4.1根据活动id获取活动信息的amout
        double amount=activityMapper.getAmountByActivityId(activityId);

        //4.2将缴费存入fee表
        feeMapper.addFee(activityId,userId,amount, false);

        //4.3构造参加活动信息
        ActivityParticipation activityParticipation=ActivityParticipation.builder()
                .activityId(activityId)
                .userId(userId)
                .isSigned(false)   //默认为未签到
                .build();

        //将数据插入活动报名表
        activityParticipationMapper.joinActivity(activityParticipation);
        return "报名活动成功！";
    }

}
