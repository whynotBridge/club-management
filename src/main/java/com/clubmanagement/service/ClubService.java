package com.clubmanagement.service;

import com.clubmanagement.model.dtos.QueryClubDTO;
import com.clubmanagement.model.pojos.Club;

import java.util.List;

public interface ClubService {
    /**
     * 获取所有未加入的社团信息
     * @return
     */
    List<QueryClubDTO> getAllClubs();

    Club selectClubByPId(int userId);

    Club getById(int clubId);

    void updateClub(Club club);

    int selectClubIdByPId(int userId);

    void insert(Club club);

    /**
     * 获取当前登录用户的社团信息
     * @return
     */
    List<QueryClubDTO> getMyclub();
}
