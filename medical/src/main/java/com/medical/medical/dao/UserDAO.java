package com.medical.medical.dao;

import com.medical.medical.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {

    String TABLE_NAME= " user ";


    @Insert("insert into" + TABLE_NAME + " (username,password,salt,real_id,head_url) values (#{username},#{password},#{salt},#{realId},#{headUrl})")
    int addUser(User user);

    @Select("select * from " + TABLE_NAME + "where username=#{username}")
    User selectByUsername(String username);

    @Select("select * from " + TABLE_NAME + "where id=#{id}")
    User selectById(int id);

    @Update("update" + TABLE_NAME + "set password=#{password} where username=#{username}")
    void updatePassword(User user);

    @Delete("delete from "+TABLE_NAME+"where username=#{username}")
    void deleteUser(User user);
}
