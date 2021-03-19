package com.emeims.dao;

import com.emeims.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 查询所有的用户信息
     * */
    List<User> getAllUser();

    /**
     * 查询特定条件的用户
     * */
    User getUserById(int id);

    /**
     * 添加用户
     * */
    void addUser(User user);

    /**
     * 更改用户信息
     * */
    void updateUser( User user);

}
