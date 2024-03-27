package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.MemberMapper;
import com.clubmanagement.model.pojos.Member;
import com.clubmanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;


    public List<Member> getByClubId(int clubId){
        return memberMapper.getByClubId(clubId);
    }

    public void joinClub(Member member){
        memberMapper.joinClub(member);
    }

    public void updatePosition(Member member){
        memberMapper.updatePosition(member);
    }

    public void deleteQuitMembers(){
        memberMapper.deleteQuitMembers();
    }

    public int getClubIdByUserId(int userid){
        return memberMapper.getClubIdByUserId(userid);
    }

    public int getMemberIdByUserId(int userId){
        return memberMapper.getMemberIdByUserId(userId);
    }
}
