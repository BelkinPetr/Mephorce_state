package com.mephorce.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Entity that represent task structure.
 * <p>
 * Created by Developer on 01.08.2017.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Subtask> subtasks;

    private String taskContent;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public Task setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) &&
                Objects.equals(getName(), task.getName()) &&
                Objects.equals(getSubtasks(), task.getSubtasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSubtasks());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subtasks=" + subtasks +
                '}';
    }
}
