package com.emeims.controller;

import com.emeims.pojo.User;
import com.emeims.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    /**
     * localhost:8080/user/allUser
     * */
    @RequestMapping("/allUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    /**
     * localhost:8080/user/getUserById
     * */
    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }


    /**
     * localhost:8080/user/updateUser
     * */
    @RequestMapping("/updateUser")
    public void updateUser(User user){
        userService.updateUser(user);
    }

}
