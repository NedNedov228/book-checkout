package com.xecore.projects.book_checkout.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Person")
public class Person {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Size(min = 10, max = 255)
    @Column(name="snp")
    @Pattern(regexp = "[A-Z][a-zA-Z]+ [A-Z][a-zA-Z]+ [A-Z][a-zA-Z]+" , message = "Incorrect format (S.N.P.)")
    private String SNP;

//    @NotEmpty(message = "E-mail should not be empty")
    @Column(name="birth_year")
    @Min(value=1900)
    private int birth_year;

    @Email
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Book> books = new ArrayList<>();

    public Person() {}

    public Person(String email, int birth_year, String SNP, int user_id) {
        this.email = email;
        this.birth_year = birth_year;
        this.SNP = SNP;
        this.user_id = user_id;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    @Min(value = 1900)
    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(@Min(value = 1900) int birth_year) {
        this.birth_year = birth_year;
    }

    public @Size(min = 10, max = 255) @Pattern(regexp = "[A-Z][a-zA-Z]+ [A-Z][a-zA-Z]+ [A-Z][a-zA-Z]+", message = "Incorrect format (S.N.P.)") String getSNP() {
        return SNP;
    }

    public void setSNP(@Size(min = 10, max = 255) @Pattern(regexp = "[A-Z][a-zA-Z]+ [A-Z][a-zA-Z]+ [A-Z][a-zA-Z]+", message = "Incorrect format (S.N.P.)") String SNP) {
        this.SNP = SNP;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
