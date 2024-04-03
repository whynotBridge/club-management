package com.clubmanagement.mapper;


import com.clubmanagement.model.dtos.SummaryInfoDTO;
import com.clubmanagement.model.pojos.Summary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface SummaryMapper {

    /**
     * 新增总结记录
     * @param activityId
     * @param summaryInfo
     */
    @Insert("insert into summary(activity_id, info) values(#{activityId},#{summaryInfo.info})")
    void save(int activityId, SummaryInfoDTO summaryInfo);

    /**
     * 根据活动id查询总结信息
     * @param activityId
     * @return
     */
    @Select("select info from summary where activity_id = #{activityId}")
    String getInfoByAId(int activityId);

    /**
     * 根据活动id修改总结信息
     * @param activityId
     * @param summaryInfo
     */
    @Update("update summary set info = #{summaryInfo.info} where activity_id = #{activityId}")
    void update(int activityId, SummaryInfoDTO summaryInfo);

}

