package com.mdevi.bookmongo.shell;

import com.mdevi.bookmongo.model.Author;
import com.mdevi.bookmongo.model.Book;
import com.mdevi.bookmongo.model.Comment;
import com.mdevi.bookmongo.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@ShellComponent
public class BookCommand {

    private final BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    //DONE: The method to add a new book to mongo db
    @ShellMethod(value = "Add a new book.", key = "addBook", group = "Book operations")
    public void addBook() {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter you book details:");
        System.out.print("ISBN code: ");
        String isbnString = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        // Gather authors info
        boolean hasAuthor = true;
        List<Author> authors = new ArrayList<>();
        System.out.print("Author full name: ");
        while (hasAuthor) {
            authors.add(new Author(sc.nextLine()));
            System.out.println("Do you wish add one more author? (y)es, (n)o");
            if (sc.nextLine().toUpperCase().equals("N")) {
                hasAuthor = false;
            } else {
                System.out.print("Author full name: ");
            }
        }
        // published date.
        System.out.print("Published: ('YYYY') ");
        Integer pubYear = Integer.parseInt(sc.nextLine());
        // book pages count.
        System.out.print("Pages: ");
        Integer pages = Integer.parseInt(sc.nextLine());
        // create a book object to save into db.
        Book book = new Book(isbnString, title, genre, pubYear, pages);
        // add book authors info.
        Author[] authorsArray = new Author[authors.size()];
        int index = 0;
        for (Author author : authors) {
            authorsArray[index++] = author;
        }
        book.setAuthors(authorsArray);
        // and finally save the book.
        bookService.addNewBook(book);
        System.out.println("The book info has been saved to store.");
    }

    //DONE: The method to find books by title.
    @ShellMethod(value = "Find book by title.", key = "findByTitle", group = "Book operations")
    public void findBookByTitle(
            @ShellOption(value = "--title") String titlePattern
    ) {
        List<Book> booksByTitle = bookService.findByTitle(titlePattern);
        if (booksByTitle.size() > 0) {
            printBooksInfo(booksByTitle);
        } else {
            System.out.println("There are no such books.");
        }
    }

    //DONE: The method to find all books.
    @ShellMethod(value = "Show all books.", key = "showAllBooks", group = "Book operations")
    public void showBooksInfo() {
        List<Book> allBooks = bookService.findAll();
        if (allBooks.size() > 0) {
            printBooksInfo(allBooks);
        } else {
            System.out.println("There are no any books available.");
        }
    }

    private void printBooksInfo(List<Book> allBooks) {
        System.out.println("______________________________________________________________________________ BOOK LIST " +
                "_________________________________________________________________________________");
        System.out.printf("| %15s | %50s | %60s | %6s | %15s | %6s |", "ISBN", "TITLE", "AUTHOR(S)", "YEAR", "GENRE", "PAGES");
        System.out.println();
        for (Book theBook : allBooks) {
            Author[] authors = theBook.getAuthors();
            String[] authorNames = new String[authors.length];
            int index = 0;
            for (Author author : authors) {
                authorNames[index++] = author.getFullName();
            }
            String authorsList = StringUtils.arrayToDelimitedString(authorNames, ", ");
            System.out.printf("| %15s | %50s | %60s | %6s | %15s | %6s |",
                    theBook.getIsbn(), theBook.getTitle(), authorsList, theBook.getPublishedYear(),
                    theBook.getGenre(), theBook.getPages());
            System.out.println();
        }
        System.out.println("______________________________________________________________________________ BOOK LIST " +
                "_________________________________________________________________________________");
    }

    //DONE: The method to find books by author name.
    @ShellMethod(value = "Find books by author name.", key = "findByAuthorName", group = "Book operations")
    public void findBooksByAuthorName(@ShellOption(value = "--name") String authorNamePattern) {
        final List<Book> booksByAuthorName = bookService.findByAuthor(authorNamePattern);
        if (booksByAuthorName.size() > 0) {
            printBooksInfo(booksByAuthorName);
        }
    }

    //DONE: The method to add comment to book.
    @ShellMethod(value = "Add a short comment to the book.", key = "commentTheBook", group = "Book operations")
    public void addCommentToBook(
            @ShellOption String isbn,
            @ShellOption String person,
            @ShellOption String text
    ) {
        bookService.addCommentToBook(isbn, person, text);
        System.out.println("Comment is added");
    }

    //DONE: The method show a book comments.
    @ShellMethod(value = "Show all comments of the book.", key = "showComments", group = "Book operations")
    public void showBookComments(
            @ShellOption String isbn
    ) {
        Map<String, List<Comment>> commentsBook = bookService.findAllCommentsByIsbn(isbn);
        if (commentsBook != null) {
            String keyBookTitle = commentsBook.keySet().stream().findFirst().get();
            Optional<List<Comment>> comments = Optional.ofNullable(commentsBook.get(keyBookTitle));
            // check if comments exist.
            if (!"".equals(keyBookTitle) && comments.isPresent() && !comments.get().isEmpty()) {
                printBookComments(keyBookTitle, commentsBook.get(keyBookTitle));
            } else {
                System.out.println("Not commented yet.");
            }
        } else System.out.println("No book found with ISBN " + isbn);
    }

    private void printBookComments(String bookTitle, List<Comment> comments) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println("Comments for selected book: " + bookTitle);
        for (Comment comment : comments) {
            System.out.println(simpleDateFormat.format(comment.getDate()) + "\t" + comment.getCommenter() + " wrote: \t" + comment.getText());
        }
    }

    //DONE: The method to delete book by ISBN.
    @ShellMethod(value = "Delete the book by ISBN.", key = "deleteBook", group = "Book operations")
    public void deleteBook(@ShellOption(value = "--isbn") String isbn) {
        bookService.deleteBook(isbn);
    }
}
