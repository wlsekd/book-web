package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.BookListMapper;
import com.javaclimb.book.dao.BookMapper;
import com.javaclimb.book.dao.UserMapper;
import com.javaclimb.book.domain.Book;
import com.javaclimb.book.domain.BookList;
import com.javaclimb.book.domain.User;
import com.javaclimb.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Resource
    private BookListMapper bookListMapper;
    /**
     * 增删改查
     *
     * @param user
     */
    @Override
    public boolean insert(User user) {
        return userMapper.insert(user)>0;
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user)>0;
    }

    @Override
    public boolean delete(Integer id) {
        List<Book> lists= bookMapper.bookOfUserId(id);
        for(Book list:lists){
            bookMapper.delete(list.getId());
        }
        List<BookList> lists1=bookListMapper.bookListOfUserId(id);
        for (BookList list:lists1){
            bookListMapper.delete(list.getId());
        }
        return userMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象,一对一
     *
     * @param id
     */
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<User> allUser() {
        return userMapper.allUser();
    }

    /**
     * 根据账号查询
     *
     * @param username
     */
    @Override
    public User UserOfUsername(String username) {
        return userMapper.UserOfUsername(username);
    }

    /**
     * 验证密码
     *
     * @param username
     * @param password
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return userMapper.verifyPassword(username,password)>0;
    }
}
