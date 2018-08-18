package com.mdevi.bookmongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document
public class Book implements Serializable {

    @Id
    private String isbn;
    @Field(value = "title")
    private String title;
    @Field(value = "genres")
    private String genre;
    @Field(value = "authors")
    private String[] Author;
    @Field("year")
    private Integer publishedYear;
    @Field("pages")
    private Integer pages;
    @Field("comments")
    private Comment[] comments;

}
