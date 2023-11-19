package com.zvaryyka.simpletaskmanager.repositories;

import com.zvaryyka.simpletaskmanager.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByLogin(String username);
    //Todo добавить методы если понадобятся
}
