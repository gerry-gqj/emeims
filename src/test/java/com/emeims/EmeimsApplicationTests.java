package com.emeims;

import com.emeims.dao.CountMapper;
import com.emeims.dao.PositionMapper;
import com.emeims.dao.UserMapper;
import com.emeims.entity.base.Stock;
import com.emeims.entity.base.User;
import com.emeims.entity.count.PurchaseCount;
import com.emeims.entity.count.UserCount;
import com.emeims.service.stockService.StockServiceImpl;
import com.emeims.service.userService.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class EmeimsApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StockServiceImpl stockService;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private CountMapper countMapper;

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



    @Test
    public void getUserByInfo(){

        HashMap map = new HashMap<>();
        map.put("userId",3);
        map.put("userEmail","qwert@user.com");
        List<User> user = userMapper.getUserByInfo(map);
        for (User user1 : user) {
            System.out.println(user1);
        }
    }

    @Test
    void test03(){
        Date date = new Date();
        System.out.println(date);
        String string = date.toString();
        System.out.println(string);

        String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date);

        System.out.println(format);


        String replaceAll = format.toString().replaceAll("-", "");

        System.out.println(replaceAll);

        String all = replaceAll+2;

        System.out.println(all);

        String purchaseId = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
                .format(new Date()).toString()
                .replaceAll("-","")+1;
    }

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
        System.out.println(String.valueOf(new Date().getTime())+123);
    }

    @Test
    public void  updateStock(){
        Map mapUpdateStock = new HashMap<>();
        mapUpdateStock.put("stockSupplier","sup");
        mapUpdateStock.put("stockMotorType","交流电机");
        mapUpdateStock.put("stockMotorModel","jl123");
        mapUpdateStock.put("stockMotorPriceIn",500);
        mapUpdateStock.put("stockMotorQuantity",10000);
        stockService.updateStock(mapUpdateStock);
    }

    @Test
    public void getAllStock(){
        List<Stock> allStock = stockService.getAllStock();
        for (Stock stock : allStock) {
            System.out.println(stock);
        }
    }

    @Test
    public void countUser(){
        List<UserCount> countUser = userMapper.countUser();
        userService.countUser();
    }


    @Test
    public void countPurchaseByDay(){
        Map map = new HashMap<>();
        Date thisDate = new Date();
        int past = 7;
        Calendar instance = Calendar.getInstance();
        instance.setTime(thisDate);
        instance.set(Calendar.DATE,instance.get(Calendar.DATE)-past);
        Date startDate = instance.getTime();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(thisDate);
        String sD = new SimpleDateFormat("yyyy-MM-dd").format(startDate);

        map.put("startTime",sD);
        map.put("endTime",today);
        List<PurchaseCount> purchaseCounts = countMapper.countPurchaseByDay(map);
        for (PurchaseCount purchaseCount : purchaseCounts) {
            System.out.println(purchaseCount);
        }
    }


}

