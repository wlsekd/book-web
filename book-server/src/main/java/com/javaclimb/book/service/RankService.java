package com.javaclimb.book.service;

import com.javaclimb.book.domain.Rank;

/**
 * 评分service层接口
 */
public interface RankService {
    /**
     * 增删改查
     */
    public boolean insert(Rank rank);


    /**
     * 查询书单总分
     */
    public int selectScoreSum(Integer bookListId);

    /**
     * 查书单总评分人数
     */
    public int selectRankNum(Integer bookListId);
    /**
     * 计算书单平均分
     */
    public int rankOfBookListId(Integer bookListId);
    /**
     * 查询书总分
     */
    public int avaBookScore(Integer bookId);

    /**
     * 查书总评分人数
     */
    public int selectBookRankNum(Integer bookId);
    /**
     * 计算书平均分
     */
    public int rankOfBookId(Integer bookId);
}
