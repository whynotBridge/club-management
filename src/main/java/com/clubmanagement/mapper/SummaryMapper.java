package com.clubmanagement.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface SummaryMapper {

    @Insert("insert into summary(activity_id, info) values(#{activityId},#{info})")
    void save(int activityId, String info);

    @Select("select info from summary where activity_id = #{activityId}")
    String getSummaryByActivityId(int activityId);

    @Update("update summary set info = #{info} where activity_id = #{activityId}")
    void update(int activityId, String info);
}

