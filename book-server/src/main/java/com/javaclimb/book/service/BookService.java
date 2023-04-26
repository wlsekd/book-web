package com.javaclimb.book.service;

import com.javaclimb.book.domain.Book;

import java.util.List;

/**
 * 书籍service接口
 */
public interface BookService {
    /**
     * 增删改查
     */
    public boolean insert(Book book);

    public boolean update(Book book);

    public boolean delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public Book selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<Book> allBook();

    /**
     * 根据书名查询
     */
    public List<Book> bookOfName(String name);

    /**
     * 根据作者查询
     */
    public List<Book> bookOfAuthorId(Integer authorId);

    /**
     * 根据书名模糊查询
     */
    public List<Book> bookLikeName(String name);

    /**
     * 根据用户查询
     */
    public List<Book> bookOfUserId(Integer userId);
}
