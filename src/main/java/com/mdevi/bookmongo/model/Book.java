package com.mdevi.bookmongo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.List;


@Document(collection = "books")
public class Book {

    private String id;
    @Field(value = "isbn")
    private String isbn;
    @Field(value = "title")
    private String title;
    @Field(value = "genre")
    private String genre;
    @Field(value = "authors")
    private Author[] Authors;
    @Field("year")
    private Integer publishedYear;
    @Field("pages")
    private Integer pages;
    @Field("comments")
    private List<Comment> comments;

    public Book() {
    }

    public Book(String isbn, String title, String genre, Integer publishedYear, Integer pages) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Author[] getAuthors() {
        return Authors;
    }

    public void setAuthors(Author[] authors) {
        Authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", Authors=" + Arrays.toString(Authors) +
                ", publishedYear=" + publishedYear +
                '}';
    }
}
