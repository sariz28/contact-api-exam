package com.involverh.exam.repository;

import com.involverh.exam.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAll();
    List<Contact> findByNameContainingIgnoreCase(String name);

    List<Contact> findByPhoneNumbersNumberContaining(String number);

}
