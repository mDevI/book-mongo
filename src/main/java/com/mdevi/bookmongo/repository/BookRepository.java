package com.mdevi.bookmongo.repository;

import com.mdevi.bookmongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByTitleContainsOrderByTitle(String title);

    @Query("{ authors: { $elemMatch: { fullName: {$regex : ?0} } } }")
    List<Book> findAllByAuthorName(String namePattern);

    Optional<Book> findBookByIsbn(String isbn);

    Optional<Book> findBookByIsbnContains(String isbn);
}
