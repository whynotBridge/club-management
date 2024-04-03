package com.clubmanagement.service.impl;

import com.clubmanagement.mapper.SummaryMapper;
import com.clubmanagement.model.dtos.SummaryInfoDTO;
import com.clubmanagement.model.pojos.Summary;
import com.clubmanagement.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    SummaryMapper summaryMapper;

    /**
     * 社长发布活动总结
     * @param activityId
     * @param summaryInfo
     */
    public String publishSummary(int activityId, SummaryInfoDTO summaryInfo){
        //先根据活动id查询，如果数据库有记录这不再发布
        String getInfo=summaryMapper.getInfoByAId(activityId);

        //如果有记录
        if(getInfo!=null)
            return "您已经发布该活动总结了，请勿重复操作！";

        //没有则新增
        summaryMapper.save(activityId, summaryInfo);
        return "发布总结成功！";
    }

    public String getSummaryByActivityId(int activityId){
        return summaryMapper.getInfoByAId(activityId);
    }

    public void update(int activityId, SummaryInfoDTO summaryInfo){
        summaryMapper.update(activityId, summaryInfo);
    }
}
