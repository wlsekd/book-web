package com.javaclimb.book.service;

import com.javaclimb.book.domain.Author;

import java.io.IOException;
import java.util.List;

/**
 * 作者的service接口
 */
public interface AuthorService {
    /**
     * 增删改查
     */
    public boolean insert(Author author);

    public boolean update(Author author);

    public boolean delete(Integer id) throws IOException;

    /**
     * 根据主键查询对象,一对一
     */
    public Author selectByPrimaryKey(Integer id);

    /**
     * 查询所有
     */
    public List<Author> allAuthor();

    /**
     * 根据作者名模糊查询
     */
    public List<Author> authorOfName(String name);

    /**
     * 根据性别查询
     */
    public List<Author> authorOfSex(Integer sex);

    /**
     * 根据地区查询
     */
    public List<Author> authorOfLocation(String location);
}
