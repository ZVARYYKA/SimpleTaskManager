package com.zvaryyka.simpletaskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @GetMapping("/task")
    public String userTask() {
        return "task/tasks";
    }
//TODO Создание контроллера для обработки задач, связанные с задачником
}