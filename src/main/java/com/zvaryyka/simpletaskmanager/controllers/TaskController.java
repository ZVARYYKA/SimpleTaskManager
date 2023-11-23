package com.zvaryyka.simpletaskmanager.controllers;

import com.zvaryyka.simpletaskmanager.models.Person;
import com.zvaryyka.simpletaskmanager.models.Task;
import com.zvaryyka.simpletaskmanager.services.PersonDetailsService;
import com.zvaryyka.simpletaskmanager.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class TaskController {
    private final PersonDetailsService personDetailsService;
    private final TaskService taskService;

    @Autowired
    public TaskController(PersonDetailsService personDetailsService, TaskService taskService) {
        this.personDetailsService = personDetailsService;
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public String userTask(Principal principal, Model model) {
        Person person =getCurrentPerson(principal);
        person.getTasks();
        model.addAttribute("person",person);
        model.addAttribute("taskForAdd",new Task());
        model.addAttribute("editTask",new Task());
        System.out.println(principal.getName());
        return "task/tasks";
    }
    @PostMapping("/createTask")
    public String createNewTask(@ModelAttribute("taskForAdd")  Task taskForAdd,Principal principal, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "task/tasks";
        Person person = getCurrentPerson(principal);
        taskForAdd.setUser(person);
        taskService.save(taskForAdd);
        return "redirect:/task";
    }
    @PostMapping("/editTask/{id}")
    public String update(@ModelAttribute("editTask")  Task editTask,Principal principal, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "task/tasks";
        Person person = getCurrentPerson(principal);
        editTask.setUser(person);
        taskService.update(id, editTask);
        return "redirect:/task";
    }

    @PostMapping("/deleteTask/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/task";
    }
    private Person getCurrentPerson(Principal principal) {
        return personDetailsService.findByLogin(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
//TODO Создание контроллера для обработки задач, связанные с задачником
}