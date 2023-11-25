package com.zvaryyka.simpletaskmanager.controllers;

import com.zvaryyka.simpletaskmanager.models.Person;
import com.zvaryyka.simpletaskmanager.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class IndexController {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public IndexController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/index")
    public String mainPageIndex(Principal principal, Model model) {
        Person person = getCurrentPerson(principal);
        model.addAttribute("person",person);



        return "main/index";
    }


    private Person getCurrentPerson(Principal principal) {
        if(principal == null) {
            return new Person();
        }
        else {
            return personDetailsService.findByLogin(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }

    }
    //TODO создание контроллера для обработки запросов на основную страницу
}
