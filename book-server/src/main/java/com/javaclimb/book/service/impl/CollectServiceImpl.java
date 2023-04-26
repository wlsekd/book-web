package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.CollectionMapper;
import com.javaclimb.book.domain.Collect;
import com.javaclimb.book.service.CollectService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectionMapper collectionMapper;
    /**
     * 增删改查
     *
     * @param collect
     */
    @Override
    public boolean insert(Collect collect) {
        return collectionMapper.insert(collect)>0;
    }

    /**
     * 根据传入的数据删除收藏
     * @param collect
     * @return
     */
    @Override
    public boolean delete(Collect collect) {
        return collectionMapper.delete(collect)>0;
    }

    /**
     * 根据用户id和书id删除收藏
     */
    @Override
    public boolean del(Integer userId,Integer bookId){
        return collectionMapper.delByUserIdAndBookId(userId,bookId)>0;
    }

    /**
     * 查询所有收藏
     */
    @Override
    public List<Collect> allCollect() {
        return collectionMapper.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     *
     * @param userId
     */
    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectionMapper.collectOfUserId(userId);
    }

    /**
     * 查询某个用户是否收藏某个歌曲或歌单
     *
     * @param bookId
     * @param userId
     */
    @Override
    public boolean existBookId(Integer bookId, Integer userId) {
        return collectionMapper.existBookId(bookId,userId)>0;
    }

    /**
     * 查询是否收藏
     */
    @Override
    public boolean exist(Collect collect){
        return collectionMapper.exist(collect)>0;
    }
}
