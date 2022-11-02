package com.eventstoday.api.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor





public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false, length = 50)
    private String name;
    @Column(name="email", nullable = false, length = 50)
    private String email;
    @Column(name="age", nullable = false, length = 50)
    private int age;
    @Column(name="password", nullable = false, length = 25)
    private String password;

}
