package com.javaclimb.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 热更新热加载
 * 1.ctrl+shift+A搜索registry...点击complier。automake。allow.when.app.running勾选
 * 2.执行快捷键ctrl+F9
 */
@SpringBootApplication
@MapperScan("com.javaclimb.book.dao")
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

}
