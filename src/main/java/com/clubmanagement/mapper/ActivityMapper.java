package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.model.pojos.Club;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 新增新活动
     * @param activity
     */
    @Insert("insert into activity (club_id, theme, description, start_time, end_time, location, amount) values (#{clubId}, #{theme}, #{description}, #{startTime}, #{endTime}, #{location}, #{amount})")
    void addActivity(Activity activity);

    /**
     * 根据社团id查询 //todo 辅助索引
     * @param clubId
     * @return
     */
    @Select("select * from activity where club_id = #{clubId}")
    List<Activity> getActivitiesByClubId(int clubId);

    /**
     * 根据主键查询活动缴费
     * @param activityId
     * @return
     */
    @Select("select amount from activity where activity_id = #{activityId}")
    double getAmountByActivityId(int activityId);

    /**
     * 根据主键查询信息
     * @param activityId
     * @return
     */
    @Select("select * from activity where activity_id = #{activityId}")
    Activity getById(int activityId);
}

