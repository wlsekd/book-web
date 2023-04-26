package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.CommentMapper;
import com.javaclimb.book.domain.Comment;
import com.javaclimb.book.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 增删改查
     *
     * @param comment
     */
    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象,一对一
     *
     * @param id
     */
    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有评论
     */
    @Override
    public List<Comment> allComment() {
        return commentMapper.allComment();
    }

    /**
     * 根据书id查询
     *
     * @param bookId
     */
    @Override
    public List<Comment> commentOfBookId(Integer bookId) {
        return commentMapper.commentOfBookId(bookId);
    }

    /**
     * 根据书单id查询
     *
     * @param bookListId
     */
    @Override
    public List<Comment> commentOfBookListId(Integer bookListId) {
        return commentMapper.commentOfBookListId(bookListId);
    }
}
