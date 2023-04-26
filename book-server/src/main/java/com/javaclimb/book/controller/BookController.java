package com.javaclimb.book.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.Book;
import com.javaclimb.book.service.BookService;
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
import java.util.List;

/**
 * 书籍控制类
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    /**
     * 添加
     */
    @RequestMapping(value = "/add")
    public Object add(Book book, @RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        //获取前端传来的参数
        String pic = "/img/bookPic/hhh.jpg";
        //上传资源文件
        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"资源上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"book";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/book/"+fileName;
        try {
            mpFile.transferTo(dest);
            book.setPic(pic);
            book.setUrl(storeUrlPath);
            boolean flag = bookService.insert(book);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"保存成功");
                jsonObject.put("avator",storeUrlPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败"+e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 根据作者id查询书籍
     */
    @RequestMapping("/bookOfAuthorId")
    public Object bookOfAuthorId(Integer authorId){
        return bookService.bookOfAuthorId(authorId);
    }

    /**
     * 修改
     * @param book
     * @return
     */
    @PostMapping("/update")
    public Object update(Book book){
        JSONObject jsonObject=new JSONObject();
        boolean flag= bookService.update(book);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Object delete(Integer id) throws IOException {
        //先查询数据库中歌曲文件对应的文件位置，并进行下面代码 根据id查询完成后写
        //"file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+
        Book book1= bookService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+book1.getUrl());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/bookPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
        JSONObject jsonObject=new JSONObject();

        boolean flag= bookService.delete(id);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    /**
     * 更新图片
     */
    @RequestMapping("/updateBookPic")
    public Object updateBookPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")int id) throws IOException {
        JSONObject jsonObject=new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        }
        Book book1= bookService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+book1.getPic());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/bookPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
        //文件名等于当前时间到毫秒+原文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"bookPic";
        File file1=new File(filePath);
        //文件路径不存在新增路径
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath="/img/bookPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Book book=new Book();
            book.setId(id);
            book.setPic(storeAvatorPath);
            boolean flag= bookService.update(book);
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
    /**
     * 更新文件
     */
    @RequestMapping("/updateBookUrl")
    public Object updateBookUrl(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")int id) throws IOException {
        JSONObject jsonObject=new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        }
        //删除原来文件
        Book book1= bookService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+book1.getUrl());
        Files.deleteIfExists(path);
        //文件名等于当前时间到毫秒+原文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"book";
        File file1=new File(filePath);
        //文件路径不存在新增路径
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath="/book/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Book book=new Book();
            book.setId(id);
            book.setUrl(storeAvatorPath);
            boolean flag= bookService.update(book);
            if(flag){//成功
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
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
     * 根据书id查询
     */
    @RequestMapping("/detail")
    public Object detail(Integer bookId){
        return bookService.selectByPrimaryKey(bookId);

    }
    /**
     * 根据书名字查询
     */
    @RequestMapping("/bookOfName")
    public Object bookOfName(String name){
        return bookService.bookOfName(name);
    }
    /**
     * 查询所有
     */
    @RequestMapping("/allBook")
    public List<Book> allBook(){
        return bookService.allBook();
    }
    /**
     * 根据书名模糊查询
     */
    @RequestMapping("/bookLikeName")
    public List<Book> bookLikeName(String name){
        return bookService.bookLikeName(name);
    }

    /**
     * 根据用户查询
     */
    @RequestMapping("/bookOfUserId")
    public List<Book> bookOfUserId(Integer userId){
        return bookService.bookOfUserId(userId);
    }
}
