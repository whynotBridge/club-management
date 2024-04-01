package com.clubmanagement.handler;

import com.clubmanagement.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ScheduledTaskHandler {

    @Autowired
    MemberService memberService;

    /**
     * 定期从member表中删除已经退出的记录
     */
    @Scheduled(cron = "0/15 * * * * ?") //从服务开启开始，每15秒触发一次
    public void deleteQuitMembers(){
        log.info("定时删除退出成员执行...：{}", LocalDateTime.now());
        memberService.deleteQuitMembers();
    }

}
