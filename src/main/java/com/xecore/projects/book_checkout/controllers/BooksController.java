package com.xecore.projects.book_checkout.controllers;

import com.xecore.projects.book_checkout.constant.Constant;
import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import com.xecore.projects.book_checkout.services.BooksService;
import com.xecore.projects.book_checkout.services.PeopleService;
import com.xecore.projects.book_checkout.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index( @RequestParam(value = "page",required = false) Integer page,
                         @RequestParam(value = "sort_by_year",required = false) boolean sortByYear,
                         Model model) {
        if (page == null) page = 0;
        model.addAttribute("books",booksService.findAllBooks(page, Constant.PAGE_SIZE ,sortByYear));
        model.addAttribute("page",page);

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, @ModelAttribute("person") Person person, Model model) {
        Book book = booksService.findBook(id);
        Person owner = book.getOwner();
        System.out.println(book);
        model.addAttribute(book);
        model.addAttribute("owner",owner);
        model.addAttribute("people",peopleService.findAllPeople());

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

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(booksService.findBook(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        booksService.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        booksService.delete(booksService.findBook(id));
        return "redirect:/books";
    }

    @PatchMapping("/{id}/deletereservation") // Delete Reservation
    public String deleteReserved(@PathVariable("id") Integer id) {
        booksService.deleteReservation(id);

        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/addreservation")
    public String addReserved(@PathVariable("id") Integer id,@ModelAttribute("person") Person person) {
        booksService.addReservation(id,person.getUser_id());
        System.out.println(person.getUser_id()+" "+id);

        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String getSearch(){
        return "books/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query){

        model.addAttribute("results", booksService.searchByTitle(query));

        return "/books/search";
    }

}
