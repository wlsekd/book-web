package com.javaclimb.book.service.impl;

import com.javaclimb.book.dao.AuthorMapper;
import com.javaclimb.book.dao.BookMapper;
import com.javaclimb.book.domain.Author;
import com.javaclimb.book.domain.Book;
import com.javaclimb.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 作者service实现类
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private BookMapper bookMapper;

    /**
     * 增删改查
     *
     * @param author
     */
    @Override
    public boolean insert(Author author) {
        return authorMapper.insert(author)>0;
    }

    @Override
    public boolean update(Author author) {
        return authorMapper.update(author)>0;
    }

    @Override
    public boolean delete(Integer id) throws IOException {
        List<Book> lists=bookMapper.bookOfAuthorId(id);
        for(Book list:lists){
            bookMapper.delete(list.getId());
            Book book= bookMapper.selectByPrimaryKey(list.getId());
            Path path = Paths.get(System.getProperty("user.dir")+book.getPic());
            Files.deleteIfExists(path);
        }
        return authorMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象,一对一
     *
     * @param id
     */
    @Override
    public Author selectByPrimaryKey(Integer id) {
        return authorMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Author> allAuthor() {
        return authorMapper.allAuthor();
    }

    /**
     * 根据作者名模糊查询
     *
     * @param name
     */
    @Override
    public List<Author> authorOfName(String name) {
        return authorMapper.authorOfName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Author> authorOfSex(Integer sex) {
        return authorMapper.authorOfSex(sex);
    }

    /**
     * 根据地区查询作者
     */
    @Override
    public List<Author> authorOfLocation(String location){
        return authorMapper.authorOfLocation(location);
    }
}
