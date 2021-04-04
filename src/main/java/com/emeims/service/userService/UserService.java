package com.emeims.service.userService;

import com.emeims.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    List<User> getAllUser();

//    User getUserById(int id);

    List<User> getUserByInfo(Map map);

    void addUser(Map map);

    void updateUser(Map map);

    void deleteUser(Map map);

}
