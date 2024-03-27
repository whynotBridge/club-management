package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Club;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClubMapper {

    @Select("select * from club")
    List<Club> getAllClubs();

    @Select("select * from club where president_id = #{userId}")
    Club selectClubByPId(int userId);

//    可以用覆盖索引，sql优化
    @Select("select club_id from club where president_id = #{userId}")
    int getCIdByPId(int userId);

    @Select("select * from club where club_id = #{clubId}")
    Club getById(int clubId);

    void updateClub(Club club);

    @Insert("insert into club(club_name,description,contact_info,activity_space,president_id) values(#{clubName},#{description},#{contactInfo},#{activitySpace},#{presidentId})")
    void insert(Club club);
}
