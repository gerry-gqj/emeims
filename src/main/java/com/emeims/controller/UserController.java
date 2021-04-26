package com.emeims.controller;

import com.emeims.entity.base.User;
import com.emeims.entity.count.UserCount;
import com.emeims.service.userService.UserServiceImpl;
import com.emeims.utils.CookieUtil;
import com.emeims.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.activation.registries.LogSupport.log;

/**
 * 用户API,用户注册登录,查询更改用户的信息
 * swagger API测试
 *http://localhost:9090/swagger-ui.html#/
 */

@Slf4j
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
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map login(String userName,
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
     * @param user User [
     *             userEmail (String not null)
     *             userPassword (String not null)
     *            ]
     * */
    @ApiOperation("用户登录验证")
    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map logup(User user,HttpServletResponse response,HttpServletRequest request) {
        log.info("邮箱地址:[{}]",user.getUserEmail());
        log.info("用户密码:[{}]",user.getUserPassword());
        Map mapReturn = new HashMap<>();
        Map map = userInfo(user.getUserEmail());
        List<User> users = userService.getUserByInfo(map);
        if (users.isEmpty()){
            mapReturn.put("status","notExist");
            mapReturn.put("msg","用户不存在");
        }else if (users.get(0).getUserStatus().equals("未激活")){
            mapReturn.put("status","inActivated");
            mapReturn.put("msg","用户未激活");
        }else if (users.get(0).getUserStatus().equals("已注销")){
            mapReturn.put("status","loggedOut");
            mapReturn.put("msg","用户已注销");
        }else if (users.get(0).getUserPassword().equals(user.getUserPassword())){
            Map<String,String> payload = new HashMap<>();
            payload.put("id",users.get(0).getUserId().toString());
            payload.put("name",users.get(0).getUserName());
            payload.put("email",users.get(0).getUserEmail());
            String token = JwtUtils.getToken(payload);

            Cookie cookie = new Cookie("token",token);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(7*24*60*60);
            response.addCookie(cookie);

            mapReturn.put("status","success");
            mapReturn.put("msg","用户登录成功");

            mapReturn.put("userId",users.get(0).getUserId());
            mapReturn.put("userName",users.get(0).getUserName());
            mapReturn.put("position",users.get(0).getPosition().getPostName());
        }
        return mapReturn;
    }

    /**
     * 验证token是否有效
     * localhost:9090/user/verify
     * */
    @ApiOperation("验证token")
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    public Map verify(){
        log.info("token有效,验证通过");
        Map map = new HashMap<>();
        map.put("state","true");
        map.put("msg","token有效");
        return map;
    }


    @ApiOperation("用户注销")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Map logout(HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap<>();
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("token")){
//                cookie.getValue();
//                cookie.setValue("");
//                cookie.setMaxAge(0);
//                cookie.setPath("/");
//            }
//        }
        Cookie cookie = new Cookie("token","");
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        map.put("msg","注销登录成功");
        map.put("state","success");
        return map;
    }


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
     * @param user User[
     *             userId (int not null)
     *             userName (String null)
     *             userGender (String null)
     *             userEmail (String null)
     *             userPassword (String null)
     *             positionId (int null)
     *             userStatus (String null)
     *             ]
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
        List<UserCount> userCounts = userService.countUser();
        System.out.println(userCounts);
        for (UserCount userCount : userCounts) {
            System.out.println(userCount);
        }
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