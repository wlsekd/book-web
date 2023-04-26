package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.RankMapper;
import com.javaclimb.book.domain.Rank;
import com.javaclimb.book.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankMapper rankMapper;
    /**
     * 增删改查
     *
     * @param rank
     */
    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank)>0;
    }

    /**
     * 查询总分
     *
     * @param bookListId
     */
    @Override
    public int selectScoreSum(Integer bookListId) {
        return rankMapper.selectScoreSum(bookListId);
    }

    /**
     * 查总评分人数
     *
     * @param bookListId
     */
    @Override
    public int selectRankNum(Integer bookListId) {
        return rankMapper.selectRankNum(bookListId);
    }

    /**
     * 计算平均分
     *
     * @param bookListId
     */
    @Override
    public int rankOfBookListId(Integer bookListId) {
        int rankNum=rankMapper.selectRankNum(bookListId);
        if(rankNum==0){
            return 5;
        }
        return rankMapper.selectScoreSum(bookListId)/rankNum;
    }

    /**
     * 查询书总分
     *
     * @param bookId
     */
    @Override
    public int avaBookScore(Integer bookId) {
        return rankMapper.avaBookScore(bookId);
    }

    /**
     * 查书总评分人数
     *
     * @param bookId
     */
    @Override
    public int selectBookRankNum(Integer bookId) {
        return rankMapper.selectBookRankNum(bookId);
    }

    /**
     * 计算书平均分
     *
     * @param bookId
     */
    @Override
    public int rankOfBookId(Integer bookId) {
        int rankNum=rankMapper.selectBookRankNum(bookId);
        if(rankNum==0){
            return 5;
        }
        return rankMapper.avaBookScore(bookId)/rankNum;
    }
}
