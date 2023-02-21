package com.involverh.exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @GetMapping("/")
    public String getContacts() {

        return "CONTACTS";
    }
}
