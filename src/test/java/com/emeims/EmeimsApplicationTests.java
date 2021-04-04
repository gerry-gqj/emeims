package com.emeims;

import com.emeims.dao.PositionMapper;
import com.emeims.dao.UserMapper;
import com.emeims.entity.User;
import com.emeims.service.userService.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private UserServiceImpl userService;

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

//    @Test
//     void getUserById(){
//        User user = userMapper.getUserById(2);
//        System.out.println(user);
//    }


//    @Test
//     void updateUser(){
//        User user = new User();
//        user.setUserStatus("已激活");
//        user.setPositionId(3);
//        user.setUserEmail("sdfsdfdkfj");
//        user.setUserGender("男");
//        user.setUserPassword("dsfsdfd");
//        user.setUserId(5);
//        userMapper.updateUser(user);
//    }
//
//
//    @Test
//     void addUser(){
//        User user = new User();
//        user.setUserEmail("user@email.com");
//        user.setUserName("李四");
//        user.setUserGender("男");
//        user.setUserPassword("qwirndf");
//        userMapper.addUser(user);
//    }
//
//    @Test
//     void getUserByInfo(){
//        Map map = new HashMap<>();
//        /** map.put("positionId",3);
//        map.put("userId",5);
//        map.put("userName","李");*/
//        map.put("userEmail","12345");
//
//        /** map.put("userGender","男");
//        map.put("userPassword","dsfsdfd");
//        */
//        List<User> user = userService.getUserByInfo(map);
//
//        if (user.isEmpty()){
//            System.out.println("success");
//        }
//
//        System.out.println(user);
//    }



//    @Test
//    public void test(){
//
//        HashMap map = new HashMap<>();
//        map.put("userId",3);
////        map.put("userEmail","qwert@user.com");
//        List<User> user = userMapper.getUserByInfo(map);
//
////        for (User user1 : user) {
////            System.out.println(user1);
////        }
//
//
//    }

//    @Test
//    void test03(){
//        Date date = new Date();
//        System.out.println(date);
//        String string = date.toString();
//        System.out.println(string);
//
//        String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date);
//
//        System.out.println(format);
//
//
//        String replaceAll = format.toString().replaceAll("-", "");
//
//        System.out.println(replaceAll);
//
//        String all = replaceAll+2;
//
//        System.out.println(all);
//
//        String purchaseId = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
//                .format(new Date()).toString()
//                .replaceAll("-","")+1;
//
//    }


    @Test
    public void test(){
        Map map = new HashMap<>();
        map.put("purchaseId",new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
                .format(new Date()).toString()
                .replaceAll("-","")+111);

        long time = new Date().getTime();
        String sR = String.valueOf(time);

        int length = sR.length();

        System.out.println(length);

        map.put("salesId1",sR);
        map.put("salesId",sR+2);

        System.out.println(map.size());

        System.out.println(map);
    }

}

