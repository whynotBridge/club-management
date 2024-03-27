package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.model.pojos.Club;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper {

    @Insert("insert into activity (club_id, theme, description, start_time, end_time, location, amount) values (#{clubId}, #{theme}, #{description}, #{startTime}, #{endTime}, #{location}, #{amount})")
    void addActivity(Activity activity);

    @Select("select * from activity where club_id = #{clubId}")
    List<Activity> getActivitiesByClubId(int clubId);

    @Select("select amount from activity where activity_id = #{activityId}")
    double getAmountByActivityId(int activityId);

    @Select("select * from activity where activity_id = #{activityId}")
    Activity getByid(int activityId);
}

