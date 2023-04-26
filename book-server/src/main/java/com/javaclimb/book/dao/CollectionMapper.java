package com.javaclimb.book.dao;

import com.javaclimb.book.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏dao
 */
@Repository
public interface CollectionMapper {
    /**
     * 增删改查
     */
    public int insert(Collect collect);

    public int delete(Collect collect);

    /**
     * 根据用户id和书本id删除收藏
     */
    public int delByUserIdAndBookId(@Param("userId") Integer userId,@Param("bookId") Integer bookId);

    /**
     * 查询所有收藏
     */
    public List<Collect> allCollect();

    /**
     * 查询某个用户的收藏列表
     */
    public List<Collect> collectOfUserId(Integer userId);
    /**
     * 查询用户是否收藏某本书
     */
    public int existBookId(@Param("bookId")Integer bookId,@Param("userId")Integer userId);
    /**
     * 查询是否收藏
     */
    public int exist(Collect collect);
}