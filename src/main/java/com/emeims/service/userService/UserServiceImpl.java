package com.emeims.service.userService;

import com.emeims.dao.UserMapper;
import com.emeims.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

//    @Override
//    public User getUserById(int id) {
//        return userMapper.getUserById(id);
//    }

    @Override
    public List<User> getUserByInfo(Map map) {
        return userMapper.getUserByInfo(map);
    }

    @Override
    public void addUser(Map map) {
        userMapper.addUser(map);
    }

    @Override
    public void updateUser(Map map) {
        userMapper.updateUser(map);
    }

    @Override
    public void deleteUser(Map map) {
        userMapper.deleteUser(map);
    }


}
