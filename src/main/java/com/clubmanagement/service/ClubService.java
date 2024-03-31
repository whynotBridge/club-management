package com.clubmanagement.service;

import com.clubmanagement.model.dtos.QueryClubDTO;
import com.clubmanagement.model.dtos.UpdateClubDTO;
import com.clubmanagement.model.pojos.Club;

import java.util.List;

public interface ClubService {
    /**
     * 获取所有的社团信息
     * @return
     */
    List<QueryClubDTO> getAllClubs();

    Club selectClubByPId(int userId);

    Club getById(int clubId);

    /**
     * 修改社团信息
     * @param clubId
     * @param updateClubDTO
     */
    String updateClub(int clubId, UpdateClubDTO updateClubDTO);

    int selectClubIdByPId(int userId);

    void insert(Club club);

    /**
     * 获取当前登录用户的社团信息
     * @return
     */
    List<QueryClubDTO> getMyclub();
}
