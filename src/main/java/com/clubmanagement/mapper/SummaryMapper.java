package com.clubmanagement.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface SummaryMapper {

    /**
     * 新增总结记录
     * @param activityId
     * @param info
     */
    @Insert("insert into summary(activity_id, info) values(#{activityId},#{info})")
    void save(int activityId, String info);

    /**
     * 根据活动id查询总结信息
     * @param activityId
     * @return
     */
    @Select("select info from summary where activity_id = #{activityId}")
    String getSummaryByActivityId(int activityId);

    /**
     * 根据活动id修改总结信息
     * @param activityId
     * @param info
     */
    @Update("update summary set info = #{info} where activity_id = #{activityId}")
    void update(int activityId, String info);
}

