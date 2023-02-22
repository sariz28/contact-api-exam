package com.involverh.exam.model;

import javax.persistence.*;

import lombok.*;

enum PhoneType {
    MOBILE,
    HOME,
    WORK,
    OTHER
};

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PhoneType type;
    private String number;
}
