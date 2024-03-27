package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("select * from member where club_id = #{clubId}")
    List<Member> getByClubId(int clubId);

    @Insert("insert into member(user_id, club_id, position, join_date) values(#{userId}, #{clubId}, #{position}, #{joinDate})")
    void joinClub(Member member);

    @Update("update member set position = #{position} where member_id = #{memberId}")
    void updatePosition(Member member);

    @Delete("delete from member where position = 'alreadyQuit'")
    void deleteQuitMembers();

    int getClubIdByUserId(int userid);

    @Select("select member_id from member where user_id = #{userId}")
    int getMemberIdByUserId(int userId);
}
