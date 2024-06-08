package com.xecore.projects.book_checkout.controllers;

import com.xecore.projects.book_checkout.models.Person;
import com.xecore.projects.book_checkout.services.BooksService;
import com.xecore.projects.book_checkout.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BooksService booksService;

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
    public String index(Model model) {

        model.addAttribute("people" , peopleService.findAllPeople());

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
        model.addAttribute("person", p);
        model.addAttribute("books", booksService.findByOwner(p));

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
