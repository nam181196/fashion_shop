package com.example.bankrate.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootRedirectController {

    @GetMapping("/")
    public String redirectToApp() {
        return "redirect:/app/";
    }
}
