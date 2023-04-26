package com.javaclimb.book.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.BookList;
import com.javaclimb.book.domain.User;
import com.javaclimb.book.service.UserService;
import com.javaclimb.book.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户控制类
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 增删查改
     */
    @PostMapping("/add")
    public Object addUser(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String username=request.getParameter("username").trim();
        String password=request.getParameter("password").trim();
        String sex=request.getParameter("sex").trim();
        String phone=request.getParameter("phone").trim();
        String email=request.getParameter("email").trim();
        String birthday=request.getParameter("birthday").trim();
        String introduction=request.getParameter("introduction").trim();
        String location=request.getParameter("location").trim();
        String avator=request.getParameter("avator").trim();
        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        User user1=userService.UserOfUsername(username);
        if(user1!=null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名重复");
            return jsonObject;
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //生日转换为date格式
        Date birthday_time=new Date();
        try {
            birthday_time=dateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存数据到对象中
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(new Byte(sex));
        user.setPhone(phone);
        user.setEmail(email);
        user.setBirthday(birthday_time);
        user.setIntroduction(introduction);
        user.setLocation(location);
        user.setAvator(avator);
        boolean flag=userService.insert(user);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 测试时记得修改为实体类传输对象
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object update(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        String username=request.getParameter("username").trim();
        String password=request.getParameter("password").trim();
        String sex=request.getParameter("sex").trim();
        String phone=request.getParameter("phone").trim();
        String email=request.getParameter("email").trim();
        String birthday=request.getParameter("birthday").trim();
        String introduction=request.getParameter("introduction").trim();
        String location=request.getParameter("location").trim();
        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //生日转换为date格式
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthday_time=new Date();
        try {
            birthday_time=dateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存数据到对象中
        User user=new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(new Byte(sex));
        user.setPhone(phone);
        user.setEmail(email);
        user.setBirthday(birthday_time);
        user.setIntroduction(introduction);
        user.setLocation(location);
        boolean flag=userService.update(user);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,2);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request) throws IOException {
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        User user= userService.selectByPrimaryKey(Integer.parseInt(id));
        Path path = Paths.get(System.getProperty("user.dir")+user.getAvator());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/userPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
        boolean flag=userService.delete(Integer.parseInt(id));
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,2);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    @RequestMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id){
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping("/allUser")
    public Object allUser(HttpServletRequest request){
        return userService.allUser();
    }


    /**
     * 更新用户图片
     */
    @RequestMapping("/updateUserPic")
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")int id) throws IOException {
        JSONObject jsonObject=new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传头像失败");
            return jsonObject;
        }
        User user1= userService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+user1.getAvator());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/userPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
//        Files.deleteIfExists(path);System.getProperty("user.dir")+"/img/userPic/hhh.jpg"
        //文件名等于当前时间到毫秒+原文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"userPic";
        File file1=new File(filePath);
        //文件路径不存在新增路径
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath="/img/userPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            User user=new User();
            user.setId(id);
            user.setAvator(storeAvatorPath);
            boolean flag= userService.update(user);
            if(flag){//成功
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"头像上传成功");
                jsonObject.put("avator",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
            return jsonObject;
        }
    }
    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public Object login(String username,String password){
        JSONObject jsonObject=new JSONObject();
        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        boolean flag=userService.verifyPassword(username,password);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put("userMsg",userService.UserOfUsername(username));
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"登录失败");
        return jsonObject;
    }

}
