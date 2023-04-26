package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.BookListMapper;
import com.javaclimb.book.dao.CollectionMapper;
import com.javaclimb.book.dao.CommentMapper;
import com.javaclimb.book.dao.ListBookMapper;
import com.javaclimb.book.domain.BookList;
import com.javaclimb.book.domain.Collect;
import com.javaclimb.book.domain.Comment;
import com.javaclimb.book.domain.ListBook;
import com.javaclimb.book.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BookListServiceImpl implements BookListService {

    @Resource
    private BookListMapper bookListMapper;
    @Autowired
    private ListBookMapper listBookMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 增删改查
     *
     * @param bookList
     */
    @Override
    public boolean insert(BookList bookList) {
        return bookListMapper.insert(bookList)>0;
    }

    @Override
    public boolean update(BookList bookList) {
        return bookListMapper.update(bookList)>0;
    }

    @Override
    public boolean delete(Integer id) {
        List<ListBook> lists=listBookMapper.listBookOfBookListId(id);
        for (ListBook list:lists){
            listBookMapper.delete(list.getId());
        }
        List<Collect> lists1= collectionMapper.allCollect();
        for (Collect list:lists1){
            if(list.getBookId()==id){
                collectionMapper.delete(list);
            }
        }
        List<Comment> lists2=commentMapper.commentOfBookListId(id);
        for(Comment list:lists2){
            commentMapper.delete(list.getId());
        }
        return bookListMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象,一对一
     *
     * @param id
     */
    @Override
    public BookList selectByPrimaryKey(Integer id) {
        return bookListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     */
    @Override
    public List<BookList> allBookList() {
        return bookListMapper.allBookList();
    }

    /**
     * 根据标题模糊查询
     *
     * @param title
     */
    @Override
    public List<BookList> bookListLikeTitle(String title) {
        return bookListMapper.bookListLikeTitle(title);
    }

    /**
     * 根据标题精确查询
     *
     * @param title
     */
    @Override
    public List<BookList> bookListOfTitle(String title) {
        return bookListMapper.bookListOfTitle(title);
    }

    /**
     * 根据风格查询
     *
     * @param style
     */
    @Override
    public List<BookList> likeStyle(String style) {
        return bookListMapper.likeStyle(style);
    }

    /**
     * 根据用户查询书单
     */
    public List<BookList> bookListOfUserId(Integer userId){
        return bookListMapper.bookListOfUserId(userId);
    }
}
