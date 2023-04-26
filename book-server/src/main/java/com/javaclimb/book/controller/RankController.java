package com.javaclimb.book.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.book.domain.Rank;
import com.javaclimb.book.service.AdminService;
import com.javaclimb.book.service.RankService;
import com.javaclimb.book.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankService rankService;
    /**
     * 新增评分
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object add(Rank rank){
        JSONObject jsonObject=new JSONObject();
        boolean flag=rankService.insert(rank);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"评分成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评分失败");
        return jsonObject;
    }
    /**
     * 获取书单评分
     */
    @RequestMapping("/avaScore")
    public Integer score(Integer bookListId){
        return rankService.rankOfBookListId(bookListId);
    }
    /**
     * 获取书评分
     */
    @RequestMapping("/avaBookScore")
    public Integer avaBookScore(Integer bookId){
        return rankService.rankOfBookId(bookId);
    }
}
