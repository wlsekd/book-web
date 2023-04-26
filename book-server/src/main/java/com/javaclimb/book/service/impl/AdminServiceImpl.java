package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.AdminMapper;
import com.javaclimb.book.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    /**
     * 验证密码是否正确
     *
     * @param username
     * @param password
     */
    @Override
    public boolean verfyPassword(String username, String password) {
        return  adminMapper.verfyPassword(username, password)>0;
    }
}
