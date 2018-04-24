package com.mephorce.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Developer on 01.08.2017.
 */
@Entity
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Task task;

    private String subtaskContent;

    @ManyToOne
    private Contractor contractor;

    private String color;

    private int orderInTask;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> comments = new ArrayList<>();

    private boolean isApproved;

    public Task getTask() {
        return task;
    }

    public Subtask setTask(Task task) {
        this.task = task;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Subtask setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSubtaskContent() {
        return subtaskContent;
    }

    public Subtask setSubtaskContent(String subtaskContent) {
        this.subtaskContent = subtaskContent;
        return this;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public Subtask setContractor(Contractor contractor) {
        this.contractor = contractor;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Subtask setColor(String color) {
        this.color = color;
        return this;
    }

    public int getOrderInTask() {
        return orderInTask;
    }

    public Subtask setOrderInTask(int orderInTask) {
        this.orderInTask = orderInTask;
        return this;
    }

    public List<String> getComments() {
        return comments;
    }

    public Subtask setComments(List<String> comments) {
        this.comments = comments;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public Subtask setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subtask)) return false;
        Subtask subtask = (Subtask) o;
        return getOrderInTask() == subtask.getOrderInTask() &&
                isApproved() == subtask.isApproved() &&
                Objects.equals(getId(), subtask.getId()) &&
                Objects.equals(getSubtaskContent(), subtask.getSubtaskContent()) &&
                Objects.equals(getContractor(), subtask.getContractor()) &&
                Objects.equals(getColor(), subtask.getColor()) &&
                Objects.equals(getComments(), subtask.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubtaskContent(), getContractor(), getColor(), getOrderInTask(), getComments(), isApproved());
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", subtaskContent='" + subtaskContent + '\'' +
                ", contractor=" + contractor +
                ", color='" + color + '\'' +
                ", orderInTask=" + orderInTask +
                ", comments=" + comments +
                ", isApproved=" + isApproved +
                '}';
    }
}
