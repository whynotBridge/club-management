package com.clubmanagement.mapper;


import com.clubmanagement.model.pojos.Admin;
import com.clubmanagement.model.pojos.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin login(String username, String password);

    @Select("select * from admin where admin_id = #{adminId}")
    Admin selectById(int adminId);
}
