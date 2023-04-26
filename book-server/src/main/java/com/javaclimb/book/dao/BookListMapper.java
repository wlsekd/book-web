package com.javaclimb.book.dao;


import com.javaclimb.book.domain.Book;
import com.javaclimb.book.domain.BookList;

import java.util.List;

public interface BookListMapper {
    /**
     * 增删改查
     */
    public int insert(BookList bookList);

    public int update(BookList bookList);

    public int delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public BookList selectByPrimaryKey(Integer id);

    /**
     * 查询所有
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
