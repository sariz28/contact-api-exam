package com.involverh.exam.controller;

import com.involverh.exam.model.Contact;
import com.involverh.exam.repository.ContactRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@Api(value="/contact", tags = "Contact Service")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    @ApiOperation(value = "Provides all contacts")
    public ResponseEntity<List<Contact>> getContacts() {

        List<Contact> contacts = contactRepository.findAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Provides contacts by name")
    public ResponseEntity<List<Contact>> getContactsByName(@PathVariable String name) {

        List<Contact> contacts = contactRepository.findByNameContainingIgnoreCase(name);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    @ApiOperation(value = "Provides contacts by phone number")
    public ResponseEntity<List<Contact>> getContactsByPhone(@PathVariable String phone) {

        List<Contact> contacts = contactRepository.findByPhoneNumbersNumberContaining(phone);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create contact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("/")
    @ApiOperation(value = "Update contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        contactRepository.save(contact);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    @ApiOperation(value = "Delete contact")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }

}
