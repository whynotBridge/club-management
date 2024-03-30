package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Club;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClubMapper {

    /**
     * 查询club表所有信息
     * @return
     */
    @Select("select * from club")
    List<Club> getAllClubs();

    /**
     * 根据社长id查询信息
     * @param userId
     * @return
     */
    @Select("select * from club where president_id = #{userId}")
    Club selectClubByPId(int userId);


    /**
     * 根据用户id查询社团id //todo 二级索引，覆盖
     * @param userId
     * @return
     */
    @Select("select club_id from club where president_id = #{userId}")
    int getCIdByPId(int userId);

    /**
     * 根据主键查询
     * @param clubId
     * @return
     */
    @Select("select * from club where club_id = #{clubId}")
    Club getById(int clubId);

    /**
     * 更新数据信息
     * @param club
     */
    void updateClub(Club club);

    /**
     * 新增数据信息
     * @param club
     */
    @Insert("insert into club(club_name,description,contact_info,activity_space,president_id) values(#{clubName},#{description},#{contactInfo},#{activitySpace},#{presidentId})")
    void insert(Club club);
}
