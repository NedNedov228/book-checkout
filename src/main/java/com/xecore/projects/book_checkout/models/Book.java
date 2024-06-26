package com.xecore.projects.book_checkout.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name="Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private int book_id;

    @Column(name = "title")
    @Size(min = 10, max = 100)
    private String title;

    @Column(name = "author_name")
    @Size(min = 10, max = 150)
    private String author_name;

    @Column(name = "year")
    @Min(value=1900)
    private int year;

    @Transient
    private boolean expired;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @ManyToOne(fetch = FetchType.EAGER)  //TODO: Make app working with FetchType.LAZY
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Person owner;

    public Book() {}
    public Book(int book_id, String title, String author_name, int year) {
        this.book_id = book_id;
        this.title = title;
        this.author_name = author_name;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author_name='" + author_name + '\'' +
                ", year=" + year +
                ", owner=" + owner +
                '}';
    }
}
