package com.javaclimb.book.service;

import com.javaclimb.book.domain.Comment;

import java.util.List;

/**
 * 评论service
 */
public interface CommentService {
    /**
     * 增删改查
     */
    public boolean insert(Comment comment);

    public boolean update(Comment comment);

    public boolean delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    public List<Comment> allComment();

    /**
     * 根据书本id查询
     */
    public List<Comment> commentOfBookId(Integer bookId);

    /**
     * 根据书单id查询
     */
    public List<Comment> commentOfBookListId(Integer bookListId);
}
