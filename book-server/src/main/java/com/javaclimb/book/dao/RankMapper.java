package com.javaclimb.book.dao;

import com.javaclimb.book.domain.Rank;
import org.springframework.stereotype.Repository;

/**
 * 评分dao
 */
@Repository
public interface RankMapper {
    /**
     * 增删改查
     */
    public int insert(Rank rank);


    /**
     * 查询书单总分
     */
    public int selectScoreSum(Integer bookListId);

    /**
     * 查书单总评分人数
     */
    public int selectRankNum(Integer bookListId);

    /**
     * 查询书的评分
     */
    public int avaBookScore(Integer bookId);

    /**
     * 查询书评分人数
     */
    public int selectBookRankNum(Integer BookId);
}