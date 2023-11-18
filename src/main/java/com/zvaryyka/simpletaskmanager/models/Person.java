package com.zvaryyka.simpletaskmanager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;


    public Person(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

}
