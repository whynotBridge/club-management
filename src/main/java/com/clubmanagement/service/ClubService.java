package com.clubmanagement.service;

import com.clubmanagement.commom.Result;
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

    Club getById(int clubId);

    /**
     * 修改社团信息
     * @param clubId
     * @param updateClubDTO
     */
    Result<?> updateClub(int clubId, UpdateClubDTO updateClubDTO);

    void insert(Club club);

    /**
     * 获取当前登录用户的社团信息
     * @return
     */
    List<QueryClubDTO> getMyclub();

}
