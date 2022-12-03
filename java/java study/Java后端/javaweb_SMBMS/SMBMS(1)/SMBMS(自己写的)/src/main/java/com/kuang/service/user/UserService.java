package com.kuang.service.user;

import com.kuang.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    //用户登录.
    public User login(String userCode,String userPassword);

    //根据用户id修改密码。
    public boolean updatePwd(int id,String password)throws SQLException;

    //查询记录数。
    public int getUserCount(String username,int userRole);

    //根据条件查询用户列表.
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    //增加用户信息。
    public boolean add(User user);

    //根据id删除user。
    public boolean deleteUserById(Integer delId);

    //修改用户信息.
    public boolean modify(User user);

    //根据ID查找user.
    public User getUserById(String id);

    //根据userCode查询出User.
    public User selectUserCodeExist(String userCode);

}
