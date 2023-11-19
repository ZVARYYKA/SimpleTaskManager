package com.zvaryyka.simpletaskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class IndexController {
    @GetMapping("/index")
    public String mainPage() {
        return "main/index";
    }
    //TODO создание контроллера для обработки запросов на основную страницу
}
