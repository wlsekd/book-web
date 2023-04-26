package com.javaclimb.book.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.Collect;
import com.javaclimb.book.domain.Comment;
import com.javaclimb.book.service.CollectService;
import com.javaclimb.book.service.CommentService;
import com.javaclimb.book.utils.Consts;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 增删查改
     */
    @PostMapping("/add")
    public Object add(Collect collect){
        JSONObject jsonObject=new JSONObject();
        if(collectService.exist(collect)){
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"已收藏");
            return jsonObject;
        }
        boolean flag=collectService.insert(collect);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;
    }


    @RequestMapping("/delete")
    public Object delete(Collect collect){
        JSONObject jsonObject=new JSONObject();
        boolean flag=collectService.delete(collect);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"取消收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"取消收藏失败");
        return jsonObject;
    }

    /**
     * 根据用户id和书id删除收藏
     */
    @RequestMapping("/del")
    public Object del(Integer userId,Integer bookId){
        JSONObject jsonObject=new JSONObject();
        boolean flag=collectService.del(userId,bookId);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"取消收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"取消收藏失败");
        return jsonObject;
    }

    @RequestMapping("/allCollect")
    public Object allCollect(){
        return collectService.allCollect();
    }

    @RequestMapping("/collectOfUserId")
    public List<Collect> collectOfUserId(Integer userId){
        return collectService.collectOfUserId(userId);
    }

    @RequestMapping("/existBookId")
    public Object existBookId(Integer bookId,Integer userId){
        JSONObject jsonObject=new JSONObject();
        boolean flag=collectService.existBookId(bookId,userId);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"已经收藏");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"未收藏");
        return jsonObject;
    }
    /**
     * 根据传入的对象判断是否收藏
     */
    @RequestMapping("/exist")
    public Object exist(Collect collect){
        JSONObject jsonObject=new JSONObject();
        boolean flag=collectService.exist(collect);
        if(flag){//成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"已经收藏");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"未收藏");
        return jsonObject;
    }

}
