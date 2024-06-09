package com.xecore.projects.book_checkout.controllers;

import com.xecore.projects.book_checkout.constant.Constant;
import com.xecore.projects.book_checkout.models.Book;
import com.xecore.projects.book_checkout.models.Person;
import com.xecore.projects.book_checkout.services.BooksService;
import com.xecore.projects.book_checkout.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BooksService booksService;

    private final int PAGE_SIZE = 4;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/people";
    }
    @GetMapping()
    public String index( @RequestParam(value = "page",required = false) Integer page, Model model) {
        if (page == null) page = 0;
        model.addAttribute("people" , peopleService.findAllPeople(page, PAGE_SIZE));
        model.addAttribute("page", page);

        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {

        // User Creation Page
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person , BindingResult bindingResult) {

        ///////// Validation

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        peopleService.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Person p = peopleService.findPerson(id);
        List<Book> books = new ArrayList<>();

        for (Book b : booksService.findByOwner(p)){
            if(b.getTakenAt()!=null)
                if(Math.abs(b.getTakenAt().getTime()- (new Date().getTime())) > Constant.EXPIRATION_DATE){
                    b.setExpired(true);
                }
            else b.setExpired(false);

            books.add(b);
        }

        model.addAttribute("person", p);
        model.addAttribute("books", books);

        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", peopleService.findPerson(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @Valid Person person, BindingResult bindingResult   ) {

        if(bindingResult.hasErrors()) {
            return "people/edit";
        }

        peopleService.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

}
