package com.clubmanagement.mapper;



import com.clubmanagement.model.enums.PayStatusEnum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FeeMapper {

    /**
     * 新增费用记录
     * @param activityId
     * @param userId
     * @param amount
     * @param payStatusEnum
     */
    @Insert("insert into fee(activity_id, user_id, amount, status) VALUES (#{activityId}, #{userId}, #{amount}, #{payStatusEnum})")
    void addFee(int activityId, int userId, double amount, PayStatusEnum payStatusEnum);

    /**
     * 根据活动id和用户id来缴费 //todo 联合索引
     * @param activityId
     * @param userId
     * @param payStatusEnum
     */
    @Update("update fee set status=#{payStatusEnum} where activity_id=#{activityId} and user_id=#{userId}")
    void PayFee(int activityId, int userId, PayStatusEnum payStatusEnum);
}

