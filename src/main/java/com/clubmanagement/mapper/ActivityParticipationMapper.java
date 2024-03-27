package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.model.pojos.ActivityParticipation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivityParticipationMapper {

    @Insert("insert into activity_participation(activity_id, user_id, username,is_signed) values(#{activityId},#{userId},#{userName},#{isSigned})")
    void joinActivity(ActivityParticipation activityParticipation);

    @Select("select * from activity_participation where activity_id=#{activityId} and user_id=#{userId}")
    ActivityParticipation getParticipationByAIdAndUId(int activityId, int userId);

    @Update("update activity_participation set is_signed=1 where activity_id=#{activityId} and user_id=#{userId}")
    void Sign(int activityId, int userId);

    @Select("select * from activity_participation where user_id=#{userId}")
    List<ActivityParticipation> getParticipationByUId(int userId);

    @Select("select * from activity_participation where activity_id=#{activityId}")
    List<ActivityParticipation> getParticipationByAId(int activityId);

    @Select("select * from activity_participation where activity_id=#{activityId} and is_signed=0")
    List<ActivityParticipation> getUnSigned(int activityId);

    @Update("update activity_participation set is_signed=1 where participation_Id=#{id}")
    void SignById(int id);
}

