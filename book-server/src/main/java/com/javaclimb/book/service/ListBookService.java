package com.javaclimb.book.service;

import com.javaclimb.book.domain.ListBook;

import java.util.List;

/**
 * 书单中书籍的service
 */
public interface ListBookService {
    /**
     * 增删改查
     */
    public boolean insert(ListBook listBook);

    public boolean update(ListBook listBook);

    public boolean delete(Integer id);

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
    public boolean deleteByBookIdAndBookListId(Integer bookId,Integer bookListId);
}
