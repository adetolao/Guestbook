package com.adetola.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login/{username}")
    public String loginUser() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
