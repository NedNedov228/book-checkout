package com.xecore.projects.book_checkout.dao;

import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAllPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(SNP,birth_year,email) VALUES (?,?,?)",
                person.getSNP(),person.getBirth_year(),person.getEmail());
    }

    public Person findPerson(int id){
     return jdbcTemplate.query("SELECT * FROM Person WHERE user_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
             .stream().findAny().orElse(null);
    }

    public void update(int id,Person updatedPerson) {

        jdbcTemplate.update("UPDATE Person SET SNP=?,birth_year=?,email=? WHERE user_id=?",
                updatedPerson.getSNP(),updatedPerson.getBirth_year(),updatedPerson.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE user_id=?",id);
    }

    public List<Book> getAllReservedBooks(int id){

        return jdbcTemplate.query("SELECT Book.title,Person.user_id FROM Person JOIN Book ON Person.user_id = Book.user_id WHERE Person.user_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
    }


}
