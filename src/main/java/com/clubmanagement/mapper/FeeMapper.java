package com.clubmanagement.mapper;



import com.clubmanagement.model.pojos.Fee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FeeMapper {

    /**
     * 新增费用记录
     * @param activityId
     * @param userId
     * @param amount
     * @param isPaid
     */
    @Insert("insert into fee(activity_id, user_id, amount, is_paid) VALUES (#{activityId}, #{userId}, #{amount}, #{isPaid})")
    void addFee(int activityId, int userId, double amount, boolean isPaid);

    /**
     * 根据活动id和用户id获取缴费信息
     * @param activityId
     * @param userId
     */
    @Select("select * from fee where activity_id = #{activityId} and user_id = #{userId}")
    Fee getByAIdAndUId(int activityId, int userId);

    /**
     * 根据活动id和用户id来缴费 //todo 联合索引
     * @param activityId
     * @param userId
     * @param isPaid
     */
    @Update("update fee set is_paid=#{isPaid} where activity_id=#{activityId} and user_id=#{userId}")
    void payFee(int activityId, int userId, boolean isPaid);


}

