package com.zvaryyka.simpletaskmanager.util;

import com.zvaryyka.simpletaskmanager.models.Person;
import com.zvaryyka.simpletaskmanager.services.PersonDetailsService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PersonValidator  {
    //TODO валидация при регистрации
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }


    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        try {
            personDetailsService.loadUserByUsername(person.getLogin());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("login", "", "Человек с таким именем пользователя уже существует");
    }
}
