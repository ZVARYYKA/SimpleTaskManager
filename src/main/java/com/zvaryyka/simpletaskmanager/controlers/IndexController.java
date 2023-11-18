package com.zvaryyka.simpletaskmanager.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class IndexController {
    @GetMapping("/index")
    public String mainPage() {
        return "index";
    }
    //TODO создание контроллера для обработки запросов на основную страницу
}
