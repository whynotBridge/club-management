package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.ActivityParticipation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivityParticipationMapper {

    /**
     * 新增申请参加活动记录
     * @param activityParticipation
     */
    @Insert("insert into activity_participation(activity_id, user_id, username,is_signed) values(#{activityId},#{userId},#{userName},#{isSigned})")
    void joinActivity(ActivityParticipation activityParticipation);

    /**
     * 根据活动id和用户id查询其参加活动信息 //todo 联合索引
     * @param activityId
     * @param userId
     * @return
     */
    @Select("select * from activity_participation where activity_id=#{activityId} and user_id=#{userId}")
    ActivityParticipation getByAIdAndUId(int activityId, int userId);

    /**
     * 根据活动id和用户id来签到 //todo 联合索引
     * @param activityId
     * @param userId
     */
    @Update("update activity_participation set is_signed=1 where activity_id=#{activityId} and user_id=#{userId}")
    void Sign(int activityId, int userId);

    /**
     * 根据用户id查询其报名参加的所有活动 //todo 二级索引
     * @param userId
     * @return
     */
    @Select("select * from activity_participation where user_id=#{userId}")
    List<ActivityParticipation> getParticipationByUId(int userId);

    /**
     * 根据活动id查询其报名情况 //todo 二级索引
     * @param activityId
     * @return
     */
    @Select("select * from activity_participation where activity_id=#{activityId}")
    List<ActivityParticipation> getByAId(int activityId);

    /**
     * 根据活动id获取所有参加活动未签到的信息
     * @param activityId
     * @return
     */
    @Select("select * from activity_participation where activity_id=#{activityId} and is_signed=0")
    List<ActivityParticipation> getAllUnSignedByAId(int activityId);

    /**
     * 根据主键来签到
     * @param id
     */
    @Update("update activity_participation set is_signed=1 where participation_Id=#{id}")
    void SignById(int id);
}

