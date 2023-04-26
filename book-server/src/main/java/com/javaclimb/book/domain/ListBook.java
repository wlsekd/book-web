package com.javaclimb.book.domain;

import java.io.Serializable;

/**
 * 书单里包括的书
 */

public class ListBook implements Serializable {
    private Integer id;
    private Integer bookId;
    private Integer bookListId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
