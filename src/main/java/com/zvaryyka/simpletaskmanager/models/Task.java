package com.zvaryyka.simpletaskmanager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "task")
    private String Task;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person user;


}
