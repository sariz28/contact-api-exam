package com.involverh.exam.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String  phoneNumber;
    private String  email;
}
