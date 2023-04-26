package com.javaclimb.book.dao;

import com.javaclimb.book.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论dao
 */
@Repository
public interface CommentMapper {
    /**
     * 增删改查
     */
    public int insert(Comment comment);

    public int update(Comment comment);

    public int delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    public List<Comment> allComment();

    /**
     * 根据书id查询
     */
    public List<Comment> commentOfBookId(Integer bookId);

    /**
     * 根据书单id查询
     */
    public List<Comment> commentOfBookListId(Integer bookListId);
}