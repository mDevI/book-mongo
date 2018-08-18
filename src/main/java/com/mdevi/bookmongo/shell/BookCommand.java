package com.mdevi.bookmongo.shell;

import com.mdevi.bookmongo.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BookCommand {

    private final BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    //TODO: The method to add a new book to mongo db

    @ShellMethod(value = "Add a new book.", key = "addBook", group = "Book operations")
    public void addBook() {

    }

    //TODO: The method to find books by name.

    //TODO: The method to find all books.

    //TODO: The method to find books by authors.

    //TODO: The method to add comment to book.


}
