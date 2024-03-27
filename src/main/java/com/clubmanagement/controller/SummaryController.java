package com.clubmanagement.controller;

import com.clubmanagement.commom.Result;
import com.clubmanagement.service.SummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/summaries")
@Api(tags = "活动总结管理")
public class SummaryController {
    @Autowired
    SummaryService summaryService;


    @PostMapping("/{activityId}")
    @ApiOperation("社长发布活动总结")
    public Result<?> summary(@PathVariable int activityId,@RequestBody String info){

        //发布活动总结
        summaryService.save(activityId,info);
        return Result.success("发布活动总结成功");
    }

    @GetMapping("/{activityId}")
    @ApiOperation("根据活动id获取活动总结")
    public Result<?> getSummaryByActivityId(@PathVariable int activityId){
        String info=summaryService.getSummaryByActivityId(activityId);
        if(info==null)
            return Result.fail("该活动暂无总结");
        return Result.success(info);
    }

    @PutMapping("/{activityId}")
    @ApiOperation("修改活动总结")
    public Result<?> updateSummary(@PathVariable int activityId,@RequestBody String info){
        summaryService.update(activityId,info);
        return Result.success("修改活动总结成功");
    }
}
