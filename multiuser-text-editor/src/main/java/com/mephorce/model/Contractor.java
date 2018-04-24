package com.mephorce.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Developer on 01.08.2017.
 */
@Entity
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String secondName;

    @OneToMany(mappedBy = "contractor", fetch = FetchType.LAZY)
    private List<Subtask> subtasks;

    public Contractor() {
    }

    public Contractor(String firstName, String secondName, List<Subtask> subtasks) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.subtasks = subtasks;
    }

    public Long getId() {
        return id;
    }

    public Contractor setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contractor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public Contractor setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public Contractor setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
        return this;
    }

    @Override
    public String toString() {
        return "имя:'" + firstName + '\'' +
                ", фамилия='" + secondName + '\'';
    }
}
