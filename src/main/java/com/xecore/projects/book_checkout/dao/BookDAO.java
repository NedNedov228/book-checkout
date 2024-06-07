package com.xecore.projects.book_checkout.dao;

import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO( SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Book").getResultList();

    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
    }

    @Transactional(readOnly = true)
    public Book findBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book b = session.get(Book.class, id);

        return b;
    }

    @Transactional
    public void update(int id,Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);

        book.setTitle(updatedBook.getTitle());
        book.setAuthor_name(updatedBook.getAuthor_name());
        book.setYear(updatedBook.getYear());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.delete(book);
    }

    @Transactional(readOnly = true)
    public Person findOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);

        Hibernate.initialize(book.getOwner());
        return book.getOwner();

    }

    @Transactional
    public void deleteReservation(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);

        book.setOwner(null);
    }

    @Transactional
    public void addReservation(int book_id,int user_id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, book_id);
        Person user = session.get(Person.class, user_id);

        book.setOwner(user);
    }

}
