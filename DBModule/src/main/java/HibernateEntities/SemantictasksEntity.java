package HibernateEntities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Вероника on 20.06.2017.
 */
@Entity
@Table(name = "semantic_tasks", schema = "anketa", catalog = "")

public class SemantictasksEntity {
    private int taskId;
    private String title;
    private String desctiption;
    private String status;
    private String prioritet;
    private Timestamp dateOfCreation;
    private Timestamp dateOfFinish;
    private Integer projId;
    private Integer studId;
    private String results;
    private String student;
    private Integer statusModer;

    @Id
    @Column(name = "task_id")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {this.taskId = taskId; }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "desctiption")
    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "prioritet")
    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }

    @Basic
    @Column(name = "date_of_creation")
    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Basic
    @Column(name = "date_of_finish")
    public Timestamp getDateOfFinish() {
        return dateOfFinish;
    }

    public void setDateOfFinish(Timestamp dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }

    @Basic
    @Column(name = "proj_id")
    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    @Basic
    @Column(name = "stud_id")
    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    @Basic
    @Column(name = "results")
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    @Basic
    @Column(name = "student")
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Basic
    @Column(name = "statusModer")
    public Integer getStatusModer() {
        return statusModer;
    }

    public void setStatusModer(Integer statusModer) {
        this.statusModer = statusModer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SemantictasksEntity that = (SemantictasksEntity) o;

        if (taskId != that.taskId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (desctiption != null ? !desctiption.equals(that.desctiption) : that.desctiption != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (prioritet != null ? !prioritet.equals(that.prioritet) : that.prioritet != null) return false;
        if (dateOfCreation != null ? !dateOfCreation.equals(that.dateOfCreation) : that.dateOfCreation != null)
            return false;
        if (dateOfFinish != null ? !dateOfFinish.equals(that.dateOfFinish) : that.dateOfFinish != null) return false;
        if (projId != null ? !projId.equals(that.projId) : that.projId != null) return false;
        if (studId != null ? !studId.equals(that.studId) : that.studId != null) return false;
        if (results != null ? !results.equals(that.results) : that.results != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (desctiption != null ? desctiption.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (prioritet != null ? prioritet.hashCode() : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (dateOfFinish != null ? dateOfFinish.hashCode() : 0);
        result = 31 * result + (projId != null ? projId.hashCode() : 0);
        result = 31 * result + (studId != null ? studId.hashCode() : 0);
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }
}
