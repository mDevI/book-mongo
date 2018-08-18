package com.mdevi.bookmongo.repository;

import com.mdevi.bookmongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
