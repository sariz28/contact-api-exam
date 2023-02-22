package com.involverh.exam.controller;

import com.involverh.exam.model.Contact;
import com.involverh.exam.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    public ResponseEntity<List<Contact>> getContacts() {

        List<Contact> contacts = contactRepository.findAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Contact>> getContactsByName(@PathVariable String name) {

        List<Contact> contacts = contactRepository.findByNameContainingIgnoreCase(name);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Contact>> getContactsByPhone(@PathVariable String phone) {

        List<Contact> contacts = contactRepository.findByPhoneNumberNumberContaining(phone);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
