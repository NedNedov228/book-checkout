package com.xecore.projects.book_checkout.services;

import com.xecore.projects.book_checkout.models.Person;
import com.xecore.projects.book_checkout.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository personRepository;

    @Autowired
    public PeopleService(PeopleRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPeople() {
        return personRepository.findAll();
    }
    public List<Person> findAllPeople(int page, int size) {
        return personRepository.findAll(PageRequest.of(page,size, Sort.by("SNP"))).toList();
    }

    public Person findPerson(Integer id) {
        Optional<Person> foundPerson = personRepository.findById(id);

        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id,Person uPerson) {
        uPerson.setUser_id(id);
        personRepository.save(uPerson);
    }

    @Transactional
    public void delete(Integer id) {
        personRepository.deleteById(id);
    }

}
