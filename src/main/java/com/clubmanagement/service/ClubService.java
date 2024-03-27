package com.clubmanagement.service;

import com.clubmanagement.model.pojos.Club;

import java.util.List;

public interface ClubService {
    List<Club> getAllClubs();

    Club selectClubByPId(int userId);

    Club getById(int clubId);

    void updateClub(Club club);

    int selectClubIdByPId(int userId);

    void insert(Club club);
}
