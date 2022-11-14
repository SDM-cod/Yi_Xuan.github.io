package com.study.dao;

import com.study.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int addUser(User user);

    int deleteUserById(@Param("id") int id);

    int updateUser(User user);

    List<User> getUsers();

    User getUserById(Integer id);

    User queryUser(String name);

    List<User>queryUserLike(String name);

}
