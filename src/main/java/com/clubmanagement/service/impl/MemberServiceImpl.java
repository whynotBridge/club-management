package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.MemberMapper;
import com.clubmanagement.mapper.UserMapper;
import com.clubmanagement.model.dtos.MemberDetailDTO;
import com.clubmanagement.model.enums.PositionEnum;
import com.clubmanagement.model.pojos.Member;
import com.clubmanagement.model.pojos.User;
import com.clubmanagement.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 根据社团ID获取成员详细信息
     * @param clubId
     * @return
     */
    public List<MemberDetailDTO> getByClubId(int clubId){
        //先根据社团id获取成员表信息
        List<Member> members=memberMapper.getByClubId(clubId);

        //构造返回队列
        List<MemberDetailDTO> memberDetailDTOS=new ArrayList<>();

        //根据获取的成员表一个个拼接详细信息
        for(Member member:members){
            //拿到userId
            int userId=member.getUserId();
            User user= userMapper.selectById(userId);

            MemberDetailDTO memberDetailDTO=new MemberDetailDTO();
            //拼接成员信息
            BeanUtils.copyProperties(member,memberDetailDTO);
            //拼接对应用户信息
            memberDetailDTO.setUsername(user.getUsername());
            memberDetailDTO.setEmail(user.getEmail());

            //加入队列
            memberDetailDTOS.add(memberDetailDTO);
        }

        return memberDetailDTOS;
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

    public int[] getClubIdByUserId(int userid){
        return memberMapper.getClubIdByUserId(userid);
    }

    public int getMemberIdByUserId(int userId){
        return memberMapper.getMemberIdByUserId(userId);
    }

//    public int[] getOnlyClubIdByUId(int userId, PositionEnum position){
//        return memberMapper.getOnlyClubIdByUId(userId, position);
//    }
}
