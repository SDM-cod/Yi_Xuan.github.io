package com.study.controller;

import com.study.pojo.User;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceIml")
    private UserService userService;
    @RequestMapping("/all")
    public String getUsers(Model model){
        List<User>users = userService.getUsers();
        model.addAttribute("users",users);
        return "allUser";
    }
    public User getUserById(){
        User user = userService.getUserById(1);
        return user;
    }
    //跳转到增加用户的页面
    @RequestMapping("/addUser")
    public String addUser(){
        return "addUser";
    }
    //增加用户
    @RequestMapping("/addUserOp")
    public String addUser0p(User user){
        userService.addUser(user);
        return "redirect:/user/all";
    }
    @RequestMapping("/toUpdateUserPaper/{id}")
    public String toUpdateUserPaper(@PathVariable("id") Integer id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "updateUser";
    }
    @RequestMapping("/UpdateUser")
    public String UpdateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/all";
    }
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
        return "redirect:/user/all";
    }
    @RequestMapping("/queryUser")
    public String queryUser(@RequestParam("queryUser") String name,Model model){
        User user = userService.queryUser(name);
        model.addAttribute("qUser",user);
        return "qUser";
    }
    @ResponseBody
    @RequestMapping("/queryUserLike")
    public List<User> getUL(String name){
        name = name + "%";
        List<User> users = userService.queryUserLike(name);
        return users;
    }
}
