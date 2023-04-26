package com.javaclimb.book.dao;

import com.javaclimb.book.domain.ListBook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书单中书籍dao
 */
@Repository
public interface ListBookMapper {
    /**
     * 增删改查
     */
    public int insert(ListBook listBook);

    public int update(ListBook listBook);

    public int delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public ListBook selectByPrimaryKey(Integer id);

    /**
     * 查询所有有书单的书
     */
    public List<ListBook> allListBook();

    /**
     * 根据书单id查询
     */
    public List<ListBook> listBookOfBookListId(Integer bookListId);

    /**
     * 根据书籍id和书单id删除字段
     */
    public int deleteByBookIdAndBookListId(Integer bookId,Integer bookListId);
}