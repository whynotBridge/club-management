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


    @Insert("insert into club_application(president_id,club_name,description,contact_info,activity_space,status) values(#{presidentId},#{clubName},#{description},#{contactInfo},#{activitySpace},#{status})")
    void insert(ClubApplication clubApplication);

    @Select("select * from club_application where status = #{applyStatusEnum}")
    List<ClubApplication> getAllApplyClubApplications(ApplyStatusEnum applyStatusEnum);

    @Select("select * from club_application where application_id = #{clubApplicationId}")
    ClubApplication selectById(int clubApplicationId);

    @Update("update club_application set status = #{status} where application_id = #{applicationId}")
    void updateById(ClubApplication clubApplication);
}
