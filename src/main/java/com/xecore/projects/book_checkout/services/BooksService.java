package com.xecore.projects.book_checkout.services;

import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import com.xecore.projects.book_checkout.repositories.BooksRepository;
import com.xecore.projects.book_checkout.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }


    public List<Book> findAllBooks() {
        return booksRepository.findAll();
    }


    public Book findBook(Integer id) {
        Optional<Book> foundBook = booksRepository.findById(id);

        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id,Book uBook) {
        uBook.setBook_id(id);
    }

    @Transactional
    public void delete(Book book) {
        booksRepository.delete(book);
    }

    @Transactional
    public void addReservation(int bId, int pId) {
        Book foundBook = findBook(bId);

        peopleRepository.findById(pId)
                .ifPresent(foundBook::setOwner);
    }

    @Transactional
    public void deleteReservation(int id) {
        booksRepository.findById(id).ifPresent(book -> {book.setOwner(null);});
    }

}
