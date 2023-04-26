package com.javaclimb.book.domain;

import java.io.Serializable;

/**
 * 评分
 */
public class Rank implements Serializable {
    private Integer id;
    private Byte type;
    private Integer bookListId;
    private Integer bookId;
    private Integer userId;
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getBookListId() {
        return bookListId;
    }

    public void setBookListId(Integer bookListId) {
        this.bookListId = bookListId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
