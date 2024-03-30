package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Admin;
import com.clubmanagement.model.pojos.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    /**
     * 根据用户名和密码查询数据库
     * @param username
     * @param password
     * @return
     */
    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin login(String username, String password);

    /**
     * 根据主键查询
     * @param adminId
     * @return
     */
    @Select("select * from admin where admin_id = #{adminId}")
    Admin selectById(int adminId);
}
