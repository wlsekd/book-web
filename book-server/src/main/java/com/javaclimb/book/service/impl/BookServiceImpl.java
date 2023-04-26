package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.*;
import com.javaclimb.book.domain.Book;
import com.javaclimb.book.domain.Collect;
import com.javaclimb.book.domain.Comment;
import com.javaclimb.book.domain.ListBook;
import com.javaclimb.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书籍service实现类
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ListBookMapper listBookMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 增删改查
     *
     * @param book
     */
    @Override
    public boolean insert(Book book) {
        return bookMapper.insert(book)>0;
    }

    @Override
    public boolean update(Book book) {
        return bookMapper.update(book)>0;
    }

    @Override
    public boolean delete(Integer id) {
        List<ListBook> lists=listBookMapper.allListBook();
        for (ListBook record:lists) {
            if(record.getBookId()==id){
                listBookMapper.delete(record.getId());
            }
        }
        List<Collect> lists1= collectionMapper.allCollect();
        for (Collect list:lists1){
            if(list.getBookId()==id){
                collectionMapper.delete(list);
            }
        }
        List<Comment> lists2=commentMapper.commentOfBookId(id);
        for(Comment list:lists2){
            commentMapper.delete(list.getId());
        }
        return bookMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象,一对一
     *
     * @param id
     */
    @Override
    public Book selectByPrimaryKey(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌曲
     */
    @Override
    public List<Book> allBook() {
        return bookMapper.allBook();
    }

    /**
     * 根据书名查询
     *
     * @param name
     */
    @Override
    public List<Book> bookOfName(String name) {
        return bookMapper.bookOfName(name);
    }

    /**
     * 根据作者查询
     *
     * @param authorId
     */
    @Override
    public List<Book> bookOfAuthorId(Integer authorId) {
        return bookMapper.bookOfAuthorId(authorId);
    }

    /**
     * 根据书名模糊查询
     *
     * @param name
     */
    @Override
    public List<Book> bookLikeName(String name) {
        return bookMapper.bookLikeName("%"+name+"%");
    }

    /**
     * 根据用户查询
     */
    @Override
    public List<Book> bookOfUserId(Integer userId){
        return bookMapper.bookOfUserId(userId);
    }
}
