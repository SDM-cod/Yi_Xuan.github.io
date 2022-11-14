package com.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/t1")
    public String t1(){
        System.out.println("控制器t1方法执行了");
        return "ok";
    }
}
