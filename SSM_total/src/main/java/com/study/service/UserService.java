package com.study.service;

import com.study.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    List<User> getUsers();

    User getUserById(Integer id);

    User queryUser(String name);

    List<User>queryUserLike(String name);
}
