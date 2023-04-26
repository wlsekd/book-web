package com.javaclimb.book.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.Book;
import com.javaclimb.book.domain.BookList;
import com.javaclimb.book.service.BookListService;
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
 * 书单控制类
 */
@RestController
@RequestMapping("/bookList")
public class BookListController {
    @Autowired
    private BookListService bookListService;

    /**
     * 增删查改
     */
    @PostMapping("/add")
    public Object add(BookList bookList){
        JSONObject jsonObject=new JSONObject();
        boolean flag= bookListService.insert(bookList);
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
     * @param bookList
     * @return
     */
    @PostMapping("/update")
    public Object update(BookList bookList){
        JSONObject jsonObject=new JSONObject();
        boolean flag= bookListService.update(bookList);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    @RequestMapping("/delete")
    public Object delete(Integer id) throws IOException {
        JSONObject jsonObject=new JSONObject();
        BookList bookList1= bookListService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+bookList1.getPic());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/bookListPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
        boolean flag= bookListService.delete(id);
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
        return bookListService.selectByPrimaryKey(id);
    }

    @RequestMapping("/allBookList")
    public Object allBookList(){
        return bookListService.allBookList();
    }

    @RequestMapping("/bookListLikeTitle")
    public Object bookListLikeTitle(String title){
        return bookListService.bookListLikeTitle("%"+title+"%");
    }
    @RequestMapping("/bookListOfTitle")
    public Object bookListOfTitle(String title){
        return bookListService.bookListOfTitle(title);
    }

    @RequestMapping("/likeStyle")
    public Object likeStyle(String style){
        return bookListService.likeStyle("%"+style+"%");
    }

    /**
     * 更新图片
     */
    @RequestMapping("/updateBookListPic")
    public Object updateBookListPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")int id) throws IOException {
        JSONObject jsonObject=new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        }
        BookList bookList1= bookListService.selectByPrimaryKey(id);
        Path path = Paths.get(System.getProperty("user.dir")+bookList1.getPic());
        if(!path.equals(Paths.get(System.getProperty("user.dir")+"/img/bookListPic/hhh.jpg"))){
            Files.deleteIfExists(path);
        }
        //文件名等于当前时间到毫秒+原文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"bookListPic";
        File file1=new File(filePath);
        //文件路径不存在新增路径
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath="/img/bookListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            BookList bookList =new BookList();
            bookList.setId(id);
            bookList.setPic(storeAvatorPath);
            boolean flag= bookListService.update(bookList);
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
        }finally {
            return jsonObject;
        }
    }

    /**
     * 根据用户查询书单
     */
    @RequestMapping("/bookListOfUserId")
    public List<BookList> bookListOfUserId(Integer userId){
        return bookListService.bookListOfUserId(userId);
    }


}
