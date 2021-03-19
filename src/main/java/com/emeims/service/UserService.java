package com.emeims.service;

import com.emeims.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    List<User> getAllUser();

    User getUserById(int id);

    void addUser(User user);

    void updateUser(User user);

}
