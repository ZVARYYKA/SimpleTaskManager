package com.zvaryyka.simpletaskmanager.controllers;

import com.zvaryyka.simpletaskmanager.models.Person;
import com.zvaryyka.simpletaskmanager.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class TaskController {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public TaskController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/task")
    public String userTask(Principal principal, Model model) {
        Person person = personDetailsService.findByLogin(principal.getName()).stream().findFirst().orElse(null);
        person.getTasks();
        model.addAttribute("person",person);
        System.out.println(principal.getName());
        return "task/tasks";
    }
//TODO Создание контроллера для обработки задач, связанные с задачником
}