package com.emeims.controller;

import com.emeims.entity.base.User;
import com.emeims.entity.count.UserCount;
import com.emeims.service.userService.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户API,用户注册登录,查询更改用户的信息
 * swagger API测试
 *http://localhost:9090/swagger-ui.html#/
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * localhost:9090/user/getUserById
     * */

    /**
    @ApiOperation("查询用户的信息")
    @RequestMapping(value = "/getUserById/{UserId}/{userEmail}/{userName}/{PositionId}",method = RequestMethod.POST)
    public User getUserByInfo(@PathVariable("UserId") int id,
                            @PathVariable("userEmail") String Email,
                            @PathVariable("userName") String userName,
                            @PathVariable("PositionId") int positionId){
        return userService.getUserById(id);
    }*/


    /**
     * @URL localhost:9090/user/logUp
     * 用户注册(只有注册页面能够注册用户)
     * @param userName (String not null)
     * @param userEmail (String not null)
     * @param userPassword (String not null)
     * @return String;
     * */
    @ApiOperation("用户注册")
    @RequestMapping(value = "/logUp",method = RequestMethod.POST)
    public Map logUp(String userName,
                       String userEmail,
                       String userPassword){

        System.out.println(userName);
        System.out.println(userEmail);
        System.out.println(userPassword);

        Map userInfoMap = userInfo(userEmail);
        List<User> users = userService.getUserByInfo(userInfoMap);

        Map mapReturn = new HashMap<>();
        if (users.isEmpty()){

            mapReturn.put("status","success");

            Map addUserMap = userInfo(userName,userEmail,userPassword);
            userService.addUser(addUserMap);
            System.out.println(addUserMap);

        }else {
            mapReturn.put("status","isExist");
        }
        return mapReturn;
    }


    /**
     * @URL localhost:9090/user/login
     * 登录验证
     * @param userEmail (String not null)
     * @param userPassword (String not null)
     * */
    @ApiOperation("用户登录验证")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map login(String userEmail,
                     String userPassword) {

        System.out.println("邮箱: "+userEmail+"密码: "+userPassword);

        Map map = userInfo(userEmail);
        List<User> users = userService.getUserByInfo(map);

//        users.get(0).getUserPassword();

        Map mapReturn = new HashMap<>();
        if (users.isEmpty()){
            mapReturn.put("status","notExist");
        }else if (users.get(0).getUserStatus().equals("未激活")){
            mapReturn.put("status","inActivated");
        }else if (users.get(0).getUserStatus().equals("已注销")){
            mapReturn.put("status","loggedOut");
        }else if (users.get(0).getUserPassword().equals(userPassword)){
            mapReturn.put("status","success");
            mapReturn.put("userId",users.get(0).getUserId());
            mapReturn.put("userName",users.get(0).getUserName());
            mapReturn.put("position",users.get(0).getPosition().getPostName());
        }
        return mapReturn;
    }

//
//
//    /**
//     * @URL  localhost:9090/user/addUser
//     * 注销用户,方式:将用户状态更改为(已注销)
//     * @param userId (int not null)
//     * */
//    @ApiOperation("注销用户")
//    @RequestMapping(value = "/logOut",method = RequestMethod.POST)
//    public String logOut(Integer userId){
//        Map map = new HashMap<>();
//        map.put("userId",userId);
//        userService.deleteUser(map);
//        return "用户已注销";
//    }
//
//
//
//    /**
//     * @URL  localhost:9090/user/addUser
//     * 激活用户,方式:将用户状态更改为(已激活)
//     * @param userId (int not null)
//     * */
//    @ApiOperation("注销用户")
//    @RequestMapping(value = "/logout",method = RequestMethod.POST)
//    public String activeUser(Integer userId){
//        Map map = new HashMap<>();
//        map.put("userId",userId);
//        userService.deleteUser(map);
//        return "用户已注销";
//    }
//





    /**
     * @URL localhost:9090/user/allUser
     * 查询所有用户信息
     * @param;
     * */
    @ApiOperation("查询所有用户的所有信息")
    @RequestMapping(value = "/allUser",method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userService.getAllUser();
    }



    /**
     * @URL localhost:9090/user/getUserByInfo
     * 查询用户信息,根据传入参数动态查询用户的信息
     * @param user userId (Integer null)
     * @param user userName (String null)
     * @param user userEmail (String null)
     * @param user userGender (String null)
     * @param user positionId (Integer null)
     * @param user userStatus (String null)
     * */
     @ApiOperation("查询用户的信息")
     @RequestMapping(value = "/getUserByInfo",method = RequestMethod.POST)
     public List<User> getUserByInfo(User user){

         System.out.println(user);
         Map map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("userName",user.getUserName());
            map.put("userEmail",user.getUserEmail());
            map.put("userGender",user.getUserGender());
            map.put("positionId",user.getPositionId());
            map.put("userStatus",user.getUserStatus());

         System.out.println(map);
         return userService.getUserByInfo(map);
     }



    /**
     * 更改用户信息,根据传入参数动态更改用户的信息
     * @param user userId (int not null)
     * @param user userName (String null)
     * @param user userEmail (String null)
     * @param user userPassword (String null)
     * @param user userGender (String null)
     * @param user positionId (int null)
     * @param user userStatus (String null)
     * localhost:9090/user/updateUser
     * */
    @ApiOperation("更改用户信息")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public Map updateUserInfo(User user){

        System.out.println(user);
        Map map = new HashMap();

            map.put("userId", user.getUserId());
            map.put("userName", user.getUserName());
            map.put("userEmail", user.getUserEmail());
            map.put("userPassword", user.getUserPassword());
            map.put("userGender", user.getUserGender());

            map.put("positionId", user.getPositionId());
            map.put("userStatus", user.getUserStatus());

        Map mapReturn = new HashMap<>();
        mapReturn.put("status","success");
        System.out.println(map);
        userService.updateUser(map);

        return mapReturn;
    }



    /**
     * 更改用户状态,根据传入参数动态更改用户的信息
     * @param  userId (int not null)
     * @param  positionId (int null)
     * @param  userStatus (String null)
     * localhost:9090/user/updateUser
     * */
    @ApiOperation("更改用户状态")
    @RequestMapping(value = "/updateUserStatus",method = RequestMethod.POST)
    public Map updateUserStatus(Integer userId,
                                Integer positionId,
                                String userStatus){
        Map map = new HashMap<>();

            map.put("userId", userId);
            map.put("positionId", positionId);
            map.put("userStatus", userStatus);

        Map mapReturn = new HashMap<>();
        if(userStatus.equals("已激活")){
            mapReturn.put("status","active success");
        }
        if(userStatus.equals("已注销")){
            mapReturn.put("status","logout success");
        }
        if(positionId==2){
            mapReturn.put("status","up success");
        }
        if(positionId==3){
            mapReturn.put("status","down success");
        }

        System.out.println(map);
        userService.updateUser(map);

        return mapReturn;
    }


    /**
     * 统计系统用户
     * localhost:9090/user/countUser
     * */
    @ApiOperation("统计用户")
    @RequestMapping(value = "/countUser",method = RequestMethod.GET)
    public List<UserCount> countUser(){
        return userService.countUser();
    }

    /**
     * 抽取重复代码
     * */
    private Map userInfo(Integer userId,
                         String userName,
                         String userPassword,
                         String userEmail,
                         String userGender,
                         Integer positionId,
                         String userStatus) {
        Map map = new HashMap<>();
        map.put("userId",userId);
        map.put("userName",userName);
        map.put("userEmail",userEmail);
        map.put("userPassword",userPassword);
        map.put("userGender",userGender);
        map.put("positionId",positionId);
        map.put("userStatus",userStatus);
        return map;
    }



    private Map userInfo(String userEmail){
        Map map = new HashMap<>();
        map.put("userEmail",userEmail);
        return map;
    }

    private Map userInfo(String userEmail,
                         String userPassword){
        Map map = new HashMap<>();
        map.put("userEmail",userEmail);
        map.put("userPassword",userPassword);
        return map;
    }

    private Map userInfo(String userName,
                         String userEmail,
                         String userPassword){
        Map map = new HashMap<>();
        map.put("userName",userName);
        map.put("userEmail",userEmail);
        map.put("userPassword",userPassword);
        return map;
    }



}

//    List<User> list = new ArrayList<User>();
//        list.add(user1);
//                list.add(user2);
//                list.add(user3);
//                list.add(user4);
//
//                System.out.println("*******Java对象 转 JSON字符串*******");
//                String str1 = JSON.toJSONString(list);
//                System.out.println("JSON.toJSONString(list)==>"+str1);
//                String str2 = JSON.toJSONString(user1);
//                System.out.println("JSON.toJSONString(user1)==>"+str2);
//
//                System.out.println("\n****** JSON字符串 转 Java对象*******");
//                User jp_user1=JSON.parseObject(str2,User.class);
//        System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);
//
//        System.out.println("\n****** Java对象 转 JSON对象 ******");
//        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
//        System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));
//
//        System.out.println("\n****** JSON对象 转 Java对象 ******");
//        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
//        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);



//<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
//<dependency>
//<groupId>com.fasterxml.jackson.core</groupId>
//<artifactId>jackson-databind</artifactId>
//<version>2.9.8</version>
//</dependency>


//<dependency>
//<groupId>com.alibaba</groupId>
//<artifactId>fastjson</artifactId>
//<version>1.2.60</version>
//</dependency>