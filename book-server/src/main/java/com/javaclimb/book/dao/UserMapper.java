package com.javaclimb.book.dao;

import com.javaclimb.book.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao
 */
@Repository
public interface UserMapper {
    /**
     * 增删改查
     */
    public int insert(User user);

    public int update(User user);

    public int delete(Integer id);

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
    public int verifyPassword(String username,String password);

}