package com.xecore.projects.book_checkout.controllers;

import com.xecore.projects.book_checkout.dao.BookDAO;
import com.xecore.projects.book_checkout.dao.PersonDAO;
import com.xecore.projects.book_checkout.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/people";
    }
    @GetMapping()
    public String index(Model model) {

        model.addAttribute("people" , personDAO.findAllPeople());

        // Get all people from DAO and send it to View
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

        personDAO.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personDAO.findPerson(id));
        model.addAttribute("books", personDAO.getAllReservedBooks(id));
        // Get Person by id using DAO and send it to View
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personDAO.findPerson(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @Valid Person person, BindingResult bindingResult   ) {

        if(bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDAO.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

//
}
