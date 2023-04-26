package com.javaclimb.book.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    /**
     * 验证密码是否正确
     */
    public int verfyPassword(String username,String password);
}
