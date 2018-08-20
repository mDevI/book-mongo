package com.mdevi.bookmongo.service;

import com.mdevi.bookmongo.dao.BookDao;
import com.mdevi.bookmongo.model.Book;
import com.mdevi.bookmongo.model.Comment;
import com.mdevi.bookmongo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDao bookDao;

    @Autowired
    public BookService(BookRepository bookRepository, BookDao bookDao) {
        this.bookRepository = bookRepository;
        this.bookDao = bookDao;
    }

    public void addNewBook(Book book) {
        //bookDao.addNewBook(book);
        bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String titlePattern) {
        final List<Book> allByTitleContainsOrderByTitle = bookRepository.findAllByTitleContainsOrderByTitle(titlePattern);
        return allByTitleContainsOrderByTitle;
    }

    public void deleteBook(String isbn) {
        Optional<Book> bookByIsbn = this.bookRepository.findBookByIsbn(isbn);
        bookByIsbn.ifPresent(bookRepository::delete);
    }

    public List<Book> findByAuthor(String authorNamePattern) {
        return bookRepository.findAllByAuthorName(authorNamePattern);
    }

    public void addCommentToBook(String isbn, String personName, String text) {
        Optional<Book> bookByIsbn = this.bookRepository.findBookByIsbn(isbn);
        if (bookByIsbn.isPresent()) {
            Comment comment = new Comment(personName, text);
            bookByIsbn.get().addComment(comment);
            bookRepository.save(bookByIsbn.get());
        }
    }

    public Map<String, List<Comment>> findAllCommentsByIsbn(String isbn) {
        Optional<Book> theBook = bookRepository.findBookByIsbn(isbn);
        return theBook.map(book -> Collections.singletonMap(book.getTitle(), book.getComments())).orElse(null);
    }
}
