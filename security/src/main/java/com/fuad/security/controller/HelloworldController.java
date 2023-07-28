package com.fuad.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/hello")
public class HelloworldController {
    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
}
