package com.clubmanagement.service.impl;

import com.clubmanagement.commom.Context;
import com.clubmanagement.mapper.ClubMapper;
import com.clubmanagement.mapper.MemberMapper;
import com.clubmanagement.model.dtos.QueryClubDTO;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.model.pojos.Member;
import com.clubmanagement.service.ClubService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubMapper clubMapper;

    @Autowired
    MemberMapper memberMapper;

    /**
     * 获取所有未加入的社团信息
     * @return
     */
    public List<QueryClubDTO> getAllClubs(){
        //思路是查询所有，如果在member表中也有记录的，返回职位，没有则职位为null

        //先查询所有社团信息
        List<Club> clubs=clubMapper.getAllClubs();

        //构造返回列表
        List<QueryClubDTO> res=new ArrayList<>();
        int userId=Context.getCurrentSession().getId(); //获取当前登入用户id

        //遍历查询到的社团
        for(Club club:clubs){
            //拼接club信息
            QueryClubDTO queryClubDTO = new QueryClubDTO();
            BeanUtils.copyProperties(club,queryClubDTO);

            //根据用户id和社团id获取成员信息
            Member member=memberMapper.getByUIdAndCId(userId,club.getClubId());
            //如果不为空，说明存在，则这个社团已经加入了，设置职位
            if(member!=null){
                queryClubDTO.setPosition(member.getPosition());
            }else{
                queryClubDTO.setPosition(null);
            }

            //加入返回列表
            res.add(queryClubDTO);
        }

        return res;
    }

    public Club selectClubByPId(int userId){
        return clubMapper.selectClubByPId(userId);
    }

    public Club getById(int clubId){
        return clubMapper.getById(clubId);
    }

    public void updateClub(Club club){
        clubMapper.updateClub(club);
    }

    public int selectClubIdByPId(int userId){
        return clubMapper.getCIdByPId(userId);
    }

    public void insert(Club club){
        clubMapper.insert(club);
    }

    /**
     * 获取当前登录用户的社团信息
     * @return
     */
    public List<QueryClubDTO> getMyclub(){
        //先根据session中的userId（社长）获取社团
        int userId = Context.getCurrentSession().getId();

        //根据用户id查询成员信息，主要使用到clubId和position
        List<Member> memberList=memberMapper.getByUserId(userId);
        if(memberList==null)
            return null;    //查询不到返回null

        //构造返回列表
        List<QueryClubDTO> res=new ArrayList<>();

        //遍历查询到的成员信息
        for(Member member:memberList){
            //根据社团Id获取社团信息
            Club club=clubMapper.getById(member.getClubId());

            //拼接社团信息
            QueryClubDTO MyclubDTO=new QueryClubDTO();
            BeanUtils.copyProperties(club,MyclubDTO);

            //拼接职位信息
            MyclubDTO.setPosition(member.getPosition());

            //加入返回列表
            res.add(MyclubDTO);
        }

        return res;
    }
}
