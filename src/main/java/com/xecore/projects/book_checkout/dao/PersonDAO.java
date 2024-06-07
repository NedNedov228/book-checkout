package com.xecore.projects.book_checkout.dao;

import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.SessionFactory;

import java.util.List;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> findAllPeople() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select p from Person p",Person.class)
                .getResultList();

        return people;
    }
    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }
    @Transactional
    public Person findPerson(int id){
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class, id);

        return person;
    }
    @Transactional
    public void update(int id,Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class, id);

        person.setSNP(updatedPerson.getSNP());
        person.setEmail(updatedPerson.getEmail());
        person.setBirth_year(updatedPerson.getBirth_year());

    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class, id);
        session.delete(person);
    }
    @Transactional
    public List<Book> getAllReservedBooks(int id){
    Session session = sessionFactory.getCurrentSession();

    Person person = (Person) session.get(Person.class, id);
        Hibernate.initialize(person.getBooks());

    return person.getBooks();
    }


}
