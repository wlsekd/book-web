package com.javaclimb.book.dao;

import com.javaclimb.book.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作者dao
 */
@Repository
public interface AuthorMapper {
    /**
     * 增删改查
     */
    public int insert(Author author);

    public int update(Author author);

    public int delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public Author selectByPrimaryKey(Integer id);

    /**
     * 查询所有作者
     */
    public List<Author> allAuthor();

    /**
     * 根据作者模糊查询
     */
    public List<Author> authorOfName(String name);

    /**
     * 根据性别查询
     */
    public List<Author> authorOfSex(Integer sex);
    /**
     * 根据地区查询作者
     */
    public List<Author> authorOfLocation(String location);
}