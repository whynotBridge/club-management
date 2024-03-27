package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.ActivityMapper;
import com.clubmanagement.mapper.SummaryMapper;
import com.clubmanagement.model.pojos.Activity;
import com.clubmanagement.service.ActivityService;
import com.clubmanagement.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    SummaryMapper summaryMapper;


    public void save(int activityId, String info){
        summaryMapper.save(activityId, info);
    }

    public String getSummaryByActivityId(int activityId){
        return summaryMapper.getSummaryByActivityId(activityId);
    }

    public void update(int activityId, String info){
        summaryMapper.update(activityId, info);
    }
}
