package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.ClubMapper;
import com.clubmanagement.model.pojos.Club;
import com.clubmanagement.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubMapper clubMapper;

    public List<Club> getAllClubs(){
        return clubMapper.getAllClubs();
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
}
