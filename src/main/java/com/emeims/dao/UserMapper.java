package com.emeims.dao;

import com.emeims.entity.base.User;
import com.emeims.entity.count.UserCount;
import org.apache.ibatis.annotations.Mapper;
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
     *
     * 废弃Api 用以测试
     *
     * */
//    User getUserById(Map map);


    /**
     * 模糊匹配所有用户的信息
     * */
    List<User> getUserByInfo(Map map);

    /**
     * 添加用户
     * */
    void addUser(Map map);

    /**
     * 更改用户信息
     * */
    void updateUser(Map map);

    /**
     * 注销用户
     * */
    void deleteUser(Map map);

    /**
     * 用户统计
     * */
    List<UserCount> countUser();

}
