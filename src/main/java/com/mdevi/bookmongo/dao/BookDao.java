package com.mdevi.bookmongo.dao;

import com.mdevi.bookmongo.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAllBooks();

    Book addNewBook(Book book);
}
