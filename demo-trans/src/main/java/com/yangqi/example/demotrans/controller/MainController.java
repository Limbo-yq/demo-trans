package com.yangqi.example.demotrans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/main")
public class MainController {

    @GetMapping(path = "/hello")
    public String hello(){
        return "Hello World";
    }
}
