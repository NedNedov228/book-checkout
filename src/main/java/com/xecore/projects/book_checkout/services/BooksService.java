package com.xecore.projects.book_checkout.services;

import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import com.xecore.projects.book_checkout.repositories.BooksRepository;
import com.xecore.projects.book_checkout.repositories.PeopleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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


    public List<Book> findAllBooks(int page, int size , boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page,size,Sort.by("year"))).toList();

        else return booksRepository.findAll(PageRequest.of(page,size)).toList();
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
        booksRepository.findById(bId).ifPresent(book -> {book.setOwner(peopleRepository.findById(pId).orElse(null)); book.setTakenAt(new Date());});

    }

    @Transactional
    public void deleteReservation(int id) {
        booksRepository.findById(id).ifPresent(book -> {book.setOwner(null); book.setTakenAt(null);});
    }

    public List<Book> searchByTitle(String query){
        if (query == null || query.isEmpty())
            return new ArrayList<>();
        return booksRepository.findByTitleStartsWith(query);
    }

}
