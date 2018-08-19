package com.mdevi.bookmongo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;
import java.time.LocalDate;

@Document
public class Comment {

    @Field("observer")
    private String commenter;
    @Field("text")
    private String text;
    @Field("create_on")
    private Date date;

    public Comment() {
    }

    public Comment(String commenter, String text) {
        this.commenter = commenter;
        this.text = text;
        this.date = Date.valueOf(LocalDate.now());
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
