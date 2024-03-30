package com.clubmanagement.mapper;


import com.clubmanagement.model.enums.ApplyStatusEnum;
import com.clubmanagement.model.pojos.ClubApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface ClubApplicationMapper {


    /**
     * 插入新数据
     * @param clubApplication
     */
    @Insert("insert into club_application(president_id,club_name,description,contact_info,activity_space,status) values(#{presidentId},#{clubName},#{description},#{contactInfo},#{activitySpace},#{status})")
    void insert(ClubApplication clubApplication);

    /**
     * 根据申请状态查询 //todo 二级
     * @param applyStatusEnum
     * @return
     */
    @Select("select * from club_application where status = #{applyStatusEnum}")
    List<ClubApplication> getAllApplyClubApplications(ApplyStatusEnum applyStatusEnum);

    /**
     * 根据主键查询
     * @param clubApplicationId
     * @return
     */
    @Select("select * from club_application where application_id = #{clubApplicationId}")
    ClubApplication selectById(int clubApplicationId);

    /**
     * 根据主键更新数据申请状态
     * @param clubApplication
     */
    @Update("update club_application set status = #{status} where application_id = #{applicationId}")
    void updateById(ClubApplication clubApplication);
}
