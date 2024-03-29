package com.clubmanagement.service;


import com.clubmanagement.model.enums.PositionEnum;
import com.clubmanagement.model.pojos.Member;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {

    List<Member> getByClubId(int clubId);

    void joinClub(Member Member);

    void updatePosition(Member member);

    void deleteQuitMembers();

    int[] getClubIdByUserId(int userid);

    int getMemberIdByUserId(int userId);

//    int getOnlyClubIdByUId(int userId, PositionEnum position);
}
