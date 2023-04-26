package com.javaclimb.book.service;

import com.javaclimb.book.domain.User;

import java.util.List;

/**
 * 用户业务层
 */
public interface UserService {
    /**
     * 增删改查
     */
    public boolean insert(User user);

    public boolean update(User user);

    public boolean delete(Integer id);

    /**
     * 根据主键查询对象,一对一
     */
    public User selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     */
    public List<User> allUser();

    /**
     * 根据账号查询
     */
    public User UserOfUsername(String username);
    /**
     * 验证密码
     */
    public boolean verifyPassword(String username,String password);
}
