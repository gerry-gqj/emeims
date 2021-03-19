package com.emeims;

import com.emeims.dao.PositionMapper;
import com.emeims.dao.UserMapper;
import com.emeims.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class EmeimsApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Test
    void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection =   dataSource.getConnection();
        System.out.println(connection);
        //关闭连接
        connection.close();
    }

    @Test
    void getAllUser(){
        List<User> allUser = userMapper.getAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }

    @Test
    void getUserById(){
        User user = userMapper.getUserById(1);
        System.out.println(user);
    }


    @Test
    void updateUser(){
        User user = new User();
        user.setStatus("已激活");
        user.setId(5);
        userMapper.updateUser(user);
    }


    @Test
    void addUser(){
        User user = new User();
        user.setEmail("user@email.com");
        user.setName("李四");
        user.setGender("男");
        user.setPassword("qwirndf");
        userMapper.addUser(user);
    }

}

