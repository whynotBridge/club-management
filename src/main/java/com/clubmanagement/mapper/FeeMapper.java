package com.clubmanagement.mapper;



import com.clubmanagement.model.Enums.PayStatusEnum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface FeeMapper {

    @Insert("insert into fee(activity_id, user_id, amount, status) VALUES (#{activityId}, #{userId}, #{amount}, #{payStatusEnum})")
    void addFee(int activityId, int userId, double amount, PayStatusEnum payStatusEnum);

    @Update("update fee set status=#{payStatusEnum} where activity_id=#{activityId} and user_id=#{userId}")
    void PayFee(int activityId, int userId, PayStatusEnum payStatusEnum);
}

