package com.xecore.projects.book_checkout.repositories;

import com.xecore.projects.book_checkout.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
