package com.javaclimb.book.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏实体
 */
public class Collect implements Serializable {
    private Integer id;
    private Integer userId;
    private Byte type;
    private Integer bookId;
    private Integer bookListId;
    private Integer authorId;
    private Date collectTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookListId() {
        return bookListId;
    }

    public void setBookListId(Integer bookListId) {
        this.bookListId = bookListId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
