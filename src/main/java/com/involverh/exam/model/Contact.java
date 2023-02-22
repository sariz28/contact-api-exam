package com.involverh.exam.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_id")
    private List<Phone> phoneNumber;
    private String  email;
}
