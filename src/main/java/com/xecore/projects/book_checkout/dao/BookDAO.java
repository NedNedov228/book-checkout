package com.xecore.projects.book_checkout.dao;

import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title,author_name,year) VALUES (?,?,?)",
                book.getTitle(),book.getAuthor_name(),book.getYear());
    }

    public Book findBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void update(int id,Book updatedBook) {

        jdbcTemplate.update("UPDATE Book SET title=?,author_name=?,year=? WHERE book_id=?",
                updatedBook.getTitle(),updatedBook.getAuthor_name(),updatedBook.getYear(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?",id);
    }

    public Person findOwner(int id) {
        return jdbcTemplate.query("select * from Person join Book on Person.user_id = Book.user_id WHERE Book.book_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);

    }

    public void deleteReservation(int id) {
        jdbcTemplate.update("UPDATE Book SET user_id=null WHERE book_id=?", id);
    }

    public void addReservation(int book_id,int user_id) {
        jdbcTemplate.update("UPDATE Book SET user_id=? WHERE book_id=?", user_id,book_id);
    }

}
