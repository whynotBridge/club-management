package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名和密码查询信息
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(String username, String password);

    /**
     * 根据主键查询
     * @param userId
     * @return
     */
    @Select("select * from user where user_id = #{userId}")
    User getById(int userId);
}
