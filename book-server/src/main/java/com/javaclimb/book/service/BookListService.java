package com.javaclimb.book.service;

import com.javaclimb.book.domain.BookList;

import java.util.List;

/**
 * 书单service interface
 */
public interface BookListService {
    /**
     * 增删改查
     */
    public boolean insert(BookList bookList);

    public boolean update(BookList bookList);

    public boolean delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public BookList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     */
    public List<BookList> allBookList();

    /**
     * 根据标题模糊查询
     */
    public List<BookList> bookListLikeTitle(String title);

    /**
     * 根据标题精确查询
     */
    public List<BookList> bookListOfTitle(String title);
    /**
     * 根据风格查询
     */
    public List<BookList> likeStyle(String style);

    /**
     * 根据用户查询书单
     */
    public List<BookList> bookListOfUserId(Integer userId);
}
