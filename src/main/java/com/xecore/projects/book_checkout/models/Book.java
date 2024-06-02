package com.xecore.projects.book_checkout.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Book {
    private String book_id;

    @Size(min = 10, max = 100)
    private String title;
    @Size(min = 10, max = 150)
    private String author_name;

    @Min(value=1900)
    private int year;

    public Book() {}
    public Book(String book_id, String title, String author_name, int year) {
        this.book_id = book_id;
        this.title = title;
        this.author_name = author_name;
        this.year = year;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
