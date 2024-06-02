package com.xecore.projects.book_checkout.controllers;

import com.xecore.projects.book_checkout.dao.BookDAO;
import com.xecore.projects.book_checkout.dao.PersonDAO;
import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books",bookDAO.findAllBooks());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, @ModelAttribute("person") Person person, Model model) {
        model.addAttribute(bookDAO.findBook(id));
        model.addAttribute("owner",bookDAO.findOwner(id));
        model.addAttribute("people",personDAO.findAllPeople());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {

        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        // Validation

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(bookDAO.findBook(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/deletereservation") // Delete Reservation
    public String deleteReserved(@PathVariable("id") Integer id) {
        bookDAO.deleteReservation(id);

        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/addreservation")
    public String addReserved(@PathVariable("id") Integer id,@ModelAttribute("person") Person person) {
        bookDAO.addReservation(id,person.getUser_id());
        System.out.println(person.getUser_id()+" "+id);

        return "redirect:/books/{id}";
    }

}
