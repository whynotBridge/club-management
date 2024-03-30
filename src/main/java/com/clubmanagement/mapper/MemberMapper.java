package com.clubmanagement.mapper;


import com.clubmanagement.model.enums.PositionEnum;
import com.clubmanagement.model.pojos.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    /**
     * 根据社团id获取所有成员信息 //todo 二级索引
     * @param clubId
     * @return
     */
    @Select("select * from member where club_id = #{clubId}")
    List<Member> getByClubId(int clubId);

    /**
     * 根据社团id获取所有成员信息 //todo 二级索引
     * @param userId
     * @return
     */
    @Select("select * from member where user_id = #{userId}")
    List<Member> getByUserId(int userId);

    /**
     * 新增成员信息
     * @param member
     */
    @Insert("insert into member(user_id, club_id, position, join_date) values(#{userId}, #{clubId}, #{position}, #{joinDate})")
    void joinClub(Member member);

    /**
     * 根据主键修改信息
     * @param member
     */
    @Update("update member set position = #{position} where member_id = #{memberId}")
    void updatePosition(Member member);

    /**
     * 根据在团状态（已退出）删除记录
     */
    @Delete("delete from member where position = 'alreadyQuit'")
    void deleteQuitMembers();

    /**
     * 根据用户id查询其加入的所有社团id
     * @param userid
     * @return
     */
    @Select("select distinct club_id from member where user_id = #{userid}")
    int[] getClubIdByUserId(int userid);

    /**
     * 根据用户id其所有成员id
     * @param userId
     * @return
     */
    @Select("select member_id from member where user_id = #{userId}")
    int getMemberIdByUserId(int userId);

    /**
     * 根据用户id和在团状态获取社团id
     * @param userId
     * @param position
     * @return
     */
    @Select("select club_id from member where user_id = #{userId} and position = #{position}")
    int[] getOnlyClubIdByUId(int userId, PositionEnum position);


    /**
     * 根据用户id和社团id获得成员信息
     * @param userId
     * @param clubId
     * @return
     */
    @Select("select * from member where user_id=#{userId} and club_id=#{clubId}")
    Member getByUIdAndCId(int userId, int clubId);
}
