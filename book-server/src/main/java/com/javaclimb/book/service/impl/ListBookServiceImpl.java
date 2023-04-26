package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.ListBookMapper;
import com.javaclimb.book.domain.ListBook;
import com.javaclimb.book.service.ListBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListBookServiceImpl implements ListBookService {
    @Autowired
    ListBookMapper listBookMapper;
    /**
     * 增删改查
     *
     * @param listBook
     */
    @Override
    public boolean insert(ListBook listBook) {
        return listBookMapper.insert(listBook)>0;
    }

    @Override
    public boolean update(ListBook listBook) {
        return listBookMapper.update(listBook)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return listBookMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象,一对一
     *
     * @param id
     */
    @Override
    public ListBook selectByPrimaryKey(Integer id) {
        return listBookMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有有书单的书
     */
    @Override
    public List<ListBook> allListBook() {
        return listBookMapper.allListBook();
    }

    /**
     * 根据书单id查询
     *
     * @param bookListId
     */
    @Override
    public List<ListBook> listBookOfBookListId(Integer bookListId) {
        List<ListBook> list= listBookMapper.listBookOfBookListId(bookListId);
        return list;
    }
    /**
     * 根据书单和书本id查询
     */
    @Override
    public boolean deleteByBookIdAndBookListId(Integer bookId,Integer bookListId){
        return listBookMapper.deleteByBookIdAndBookListId(bookId,bookListId)>0;
    }
}
