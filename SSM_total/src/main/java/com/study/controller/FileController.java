package com.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class FileController {
    @RequestMapping("/upload1")
    //将前端传递的名称为file的参数绑定为CommonsMultipartFile对象
    //批量上传则设置为数组即可
    public String fileUpload(@RequestParam("file")CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        //获取文件名
        String uploadFileName = file.getOriginalFilename();
        //如果上传的文件名为空,返回提示字符串
        if("".equals(uploadFileName)){
            return "fail";
        }
        //设置上传文件的保存路径，此路径是项目路径下的upload文件夹
        String path = request.getServletContext().getRealPath("/upload");
        File realpath = new File(path);
        //路径不存在就创建一个
        if(!realpath.exists()){
            realpath.mkdir();
        }
        //文件读写操作
        InputStream is = file.getInputStream();//文件输入流
        OutputStream os = new FileOutputStream(new File(realpath,uploadFileName));
        int len = 0;
        byte [] buffer = new byte[1024];
        while ((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
            os.flush();
        }
        os.close();
        is.close();
        return "success";
    }
    @RequestMapping("/upload2")
    public String fileUpload2(@RequestParam("file")CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        //设置上传文件的保存路径,此路径是项目路径下的upload文件夹
        String path = request.getServletContext().getRealPath("/upload");
        File realpath = new File(path);
        //路径不存在就创建一个
        if(!realpath.exists()){
            realpath.mkdir();
        }
        //通过CommonsMultipartFile方法直接写文件
        file.transferTo(new File(realpath + "/" + file.getOriginalFilename()));
        return "success";
    }
    @RequestMapping("/download")
    public String downloads(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //下载的文件路径
        String path = request.getServletContext().getRealPath("/upload");
        //下载的文件名
        String fileName = path.substring(path.lastIndexOf("/")+1);
        //设置响应头
        response.reset();//页面不缓存
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");//二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"utf-8"));

        File file = new File(path,fileName);
        //文件流读写
        //文件读写操作
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len=is.read(buffer))!=-1){
            os.write(buffer);
            os.flush();
        }
        os.close();
        is.close();
        return "success";

    }
}
