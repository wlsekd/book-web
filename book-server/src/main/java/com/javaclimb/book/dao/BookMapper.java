package com.javaclimb.book.dao;

import com.javaclimb.book.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书本dao
 */
@Repository
public interface BookMapper {
    /**
     * 增删改查
     */
    public int insert(Book book);

    public int update(Book book);

    public int delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public Book selectByPrimaryKey(Integer id);

    /**
     * 查询所有书
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