package com.javaclimb.book.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.ListBook;
import com.javaclimb.book.service.ListBookService;
import com.javaclimb.book.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 书单书本管理
 */
@RestController
@RequestMapping("/listBook")
public class ListBookController {
    @Autowired
    private ListBookService listBookService;
    /**
     * 给书单添加书籍
     */
    @RequestMapping(value = "/add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //获取前端传来的参数
        String bookId = request.getParameter("bookId").trim();
        String bookListId = request.getParameter("bookListId").trim();
        ListBook listBook =new ListBook();
        listBook.setBookId(Integer.parseInt(bookId));
        listBook.setBookListId(Integer.parseInt(bookListId));
        boolean flag= listBookService.insert(listBook);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"保存失败");
        return jsonObject;
    }

    /**
     * 根据书单id查询书
     */
    @RequestMapping("/detail")
    public Object listBookOfBookListId(HttpServletRequest request){
        String bookListId=request.getParameter("bookListId");
        return listBookService.listBookOfBookListId(Integer.parseInt(bookListId));
    }

    /**
     * 修改
     * 舍弃
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object update(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        String bookId = request.getParameter("bookId").trim();  //歌曲id
        String bookListId = request.getParameter("bookListId").trim();
        //保存数据到对象中
        ListBook listBook =new ListBook();
        listBook.setBookId(Integer.parseInt(bookId));
        listBook.setBookListId(Integer.parseInt(bookListId));
        listBook.setId(Integer.parseInt(id));
        boolean flag= listBookService.update(listBook);
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
     * 根据书籍id和书单id删除字段
     */
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request) throws IOException {
        String bookId=request.getParameter("bookId").trim();
        String bookListId=request.getParameter("bookListId").trim();
        JSONObject jsonObject=new JSONObject();
        boolean flag= listBookService.deleteByBookIdAndBookListId(Integer.parseInt(bookId),Integer.parseInt(bookListId));
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

}
