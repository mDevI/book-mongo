package com.mdevi.bookmongo.dao;

import com.mdevi.bookmongo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> getAllBooks() {
        return mongoTemplate.findAll(Book.class);
    }

    @Override
    public Book addNewBook(Book book) {
        mongoTemplate.save(book);
        return book;
    }
}
