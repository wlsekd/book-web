package com.javaclimb.book.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.Author;
import com.javaclimb.book.domain.Book;
import com.javaclimb.book.service.AuthorService;
import com.javaclimb.book.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
import java.util.List;

/**
 * 作者控制类
 */
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    /**
     * 增删查改
     */
    @PostMapping("/add")
    public Object add(Author author){
        JSONObject jsonObject=new JSONObject();
        boolean flag= authorService.insert(author);
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
     * @param author
     * @return
     */
    @PostMapping("/update")
    public Object update(Author author){
        JSONObject jsonObject=new JSONObject();
        boolean flag= authorService.update(author);
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
    public Object delete(Integer id) throws IOException {
        JSONObject jsonObject=new JSONObject();
        Author author1= authorService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+author1.getPic());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/authorPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
        boolean flag= authorService.delete(id);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    @RequestMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id){
        return authorService.selectByPrimaryKey(id);
    }

    @RequestMapping("/allAuthor")
    public Object allAuthor(){
        return authorService.allAuthor();
    }

    @RequestMapping("/authorOfName")
    public Object authorOfName(String name){
        return authorService.authorOfName("%"+name+"%");
    }

    @RequestMapping("/authorOfSex")
    public Object authorOfSex(Integer sex){
        return authorService.authorOfSex(sex);
    }

    @RequestMapping("/authorOfLocation")
    public List<Author> authorOfLocation(String location){
        return authorService.authorOfLocation("%"+location+"%");
    }

    /**
     * 更新作者图片
     */
    @RequestMapping("/updateAuthorPic")
    public Object updateAuthorPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")int id) throws IOException {
        JSONObject jsonObject=new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        }
        Author author1= authorService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+author1.getPic());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/authorPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
        //文件名等于当前时间到毫秒+原文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"authorPic";
        File file1=new File(filePath);
        //文件路径不存在新增路径
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath="/img/authorPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Author author =new Author();
            author.setId(id);
            author.setPic(storeAvatorPath);
            boolean flag= authorService.update(author);
            if(flag){//成功
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
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


}
