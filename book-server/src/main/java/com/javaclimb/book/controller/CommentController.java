package com.javaclimb.book.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.Comment;
import com.javaclimb.book.service.CommentService;
import com.javaclimb.book.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论控制类
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 增删查改
     */
    @PostMapping("/add")
    public Object add(Comment comment){
        JSONObject jsonObject=new JSONObject();
        //保存数据到对象中
        boolean flag=commentService.insert(comment);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评论失败");
        return jsonObject;
    }

    /**
     * 测试时记得修改为实体类传输对象
     * @param
     * @return
     */
    @PostMapping("/update")
    public Object update(Comment comment){
        JSONObject jsonObject=new JSONObject();
        boolean flag=commentService.update(comment);
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
    public Object delete(Integer id){
        JSONObject jsonObject=new JSONObject();
        boolean flag=commentService.delete(id);
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
        return commentService.selectByPrimaryKey(id);
    }

    @RequestMapping("/allComment")
    public Object allComment(){
        return commentService.allComment();
    }

    @RequestMapping("/commentOfBookId")
    public List<Comment> commentOfBookId(Integer bookId){
        return commentService.commentOfBookId(bookId);
    }

    @RequestMapping("/commentOfBookListId")
    public List<Comment> commentOfBookListId(Integer bookListId){
        return commentService.commentOfBookListId(bookListId);
    }
    /**
     * 评论点赞
     */
    @RequestMapping("/like")
    public Object like(Integer id,Integer up){
        JSONObject jsonObject=new JSONObject();
        Comment comment=new Comment();
        comment.setId(id);
        comment.setUp(up);
        boolean flag=commentService.update(comment);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"点赞失败");
        return jsonObject;
    }

}
