package com.clubmanagement.service.impl;

import com.clubmanagement.commom.Context;
import com.clubmanagement.mapper.MemberMapper;
import com.clubmanagement.mapper.UserMapper;
import com.clubmanagement.model.dtos.MemberDetailDTO;
import com.clubmanagement.model.enums.PositionEnum;
import com.clubmanagement.model.pojos.Member;
import com.clubmanagement.model.pojos.User;
import com.clubmanagement.service.MemberService;
import com.clubmanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    UserService userService;


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
            User user= userService.getById(userId);

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

    /**
     * 用户加入社团
     * @param clubId
     */
    public String joinClub(int clubId){
        //根据session中的userId获取用户id
        int userId = Context.getCurrentSession().getId();

        //先根据用户id和社团id查询成员表
        Member member=memberMapper.getByUIdAndCId(userId,clubId);

        //如果已经存在
        if(member!=null)
            return "您已经申请加入该社团了，请勿重复操作！";

        //不存在，先构造信息
        member=Member.builder().userId(userId).clubId(clubId)
                .position(PositionEnum.applyJoin)   //申请加入
                .joinDate(LocalDateTime.now())
                .build();

        //插入成员表
        memberMapper.joinClub(member);

        return "申请加入成功！";
    }

    /**
     * 用户退出社团
     * @param clubId
     */
    public String quitClub(int clubId){
        //根据session获得userId
        int userId = Context.getCurrentSession().getId();
        //根据userId和clubId获取member
        Member member=memberMapper.getByUIdAndCId(userId,clubId);

        //如果已经更改
        if(member.getPosition().equals(PositionEnum.applyQuit))
            return "你已经申请退出该社团了，请勿重复申请！";

        //设置职位信息
        member.setPosition(PositionEnum.applyQuit);

        //更改记录
        memberMapper.updatePositionById(member);
        return "申请退出成功！";
    }

    /**
     * 社长修改成员职位
     * @param memberId
     * @param position 不同职位
     */
    public void updatePosition(int memberId,PositionEnum position){
        Member member = Member.builder().memberId(memberId).position(position).build();
        memberMapper.updatePositionById(member);
    }

    /**
     * 每隔一分钟删除member表退出的成员
     */
    @Transactional
    public void deleteQuitMembers(){
        memberMapper.deleteQuitMembers();
    }

}
