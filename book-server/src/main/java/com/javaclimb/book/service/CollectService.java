package com.javaclimb.book.service;

import com.javaclimb.book.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收藏service接口
 */
public interface CollectService {
    /**
     * 增删改查
     */
    public boolean insert(Collect collect);

    public boolean delete(Collect collect);

    /**
     * 根据用户id和书本id删除收藏
     */
    public boolean del(Integer userId,Integer bookId);

    /**
     * 查询所有收藏
     */
    public List<Collect> allCollect();

    /**
     * 查询某个用户的收藏列表
     */
    public List<Collect> collectOfUserId(Integer userId);
    /**
     * 查询某个用户是否收藏某本书
     */
    public boolean existBookId(@Param("bookId")Integer bookId, @Param("userId")Integer userId);

    /**
     * 查询是否收藏
     */
    public boolean exist(Collect collect);
}
