package com.clubmanagement.service;


import com.clubmanagement.model.dtos.MemberDetailDTO;
import com.clubmanagement.model.enums.PositionEnum;
import com.clubmanagement.model.pojos.Member;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {

    /**
     *  根据社团ID获取成员详细信息
     * @param clubId
     * @return
     */
    List<MemberDetailDTO> getByClubId(int clubId);

    /**
     * 用户加入社团
     * @param clubId
     */
    String joinClub(int clubId);

    /**
     * 用户退出社团
     * @param clubId
     */
    String quitClub(int clubId);

    /**
     * 社长修改成员职位，包括审核申请和退出
     * @param memberId
     * @param position
     */
    void updatePosition(int memberId,PositionEnum position);

    /**
     * 每隔一分钟删除member表退出的成员
     */
    void deleteQuitMembers();

    int[] getClubIdByUserId(int userid);

    int getMemberIdByUserId(int userId);



//    int getOnlyClubIdByUId(int userId, PositionEnum position);
}
