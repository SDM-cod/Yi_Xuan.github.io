package com.kuang.service.user;

import com.kuang.dao.BaseDao;
import com.kuang.dao.user.UserDao;
import com.kuang.dao.user.UserDaoImpl;
import com.kuang.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/*
service层捕获异常，进行事务处理.
事务处理：调用不同dao的多个方法，必须使用同一个connection（connection作为参数传递）.
事务完成之后，需要在service层进行connection的关闭，在dao层关闭（PreparedStatement和ResultSet对象）.
 */
public class UserServiceImpl implements UserService{

    //业务层都会调用dao层，所以我们要引入Dao层。
    private UserDao userDao;
    public UserServiceImpl() {//无参构造器。
        userDao = new UserDaoImpl();
    }


    //用户登录
    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的具体的数据库操作。
            user = userDao.getLoginUser(connection, userCode,userPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return user;
    }


    //根据用户id修改密码。
    @Override
    public boolean updatePwd(int id, String pwd) throws SQLException {
        Connection connection = null;
        boolean flag = false;
        //修改密码。
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection,id,pwd)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }


    //查询记录数。
    @Override
    public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int count = 0;

        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection,username,userRole);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection,null,null);
        }

        return count;
    }


    //根据条件查询用户列表
    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;

        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, queryUserName, queryUserRole, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return userList;
    }


    //增加用户信息。
    @Override
    public boolean add(User user) {
        Connection connection = null;
        int addRows = 0;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);//开启JDBC事务。
            addRows = userDao.add(connection,user);
            connection.commit();
            if (addRows>0){
                flag = true;
                System.out.println("add success!");
            }else {
                System.out.println("add failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("========rollback=========");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            //在service层进行connection连接的关闭.
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }


    //根据id删除user。
    @Override
    public boolean deleteUserById(Integer delId) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            if (userDao.deleteUserById(connection,delId) > 0){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }


    //修改用户信息.
    @Override
    public boolean modify(User user) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            if (userDao.modify(connection,user)>0){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }


    //根据ID查找user.
    @Override
    public User getUserById(String id) {
        User user = null;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getUserById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        } finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return user;
    }


    //根据userCode查询出User.
    @Override
    public User selectUserCodeExist(String userCode) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser2(connection,userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return user;
    }

}
