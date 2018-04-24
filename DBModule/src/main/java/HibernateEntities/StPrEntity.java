package HibernateEntities;

import javax.persistence.*;

/**
 * Created by Вероника on 24.05.2017.
 */
@Entity
@Table(name = "st_pr", schema = "anketa", catalog = "")
public class StPrEntity {
    private int id;
    private Integer taskId;
    private Integer prId;
    private Integer stId;
    private Integer semtaskId;


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "task_id")
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "pr_id")
    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    @Basic
    @Column(name = "st_id")
    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    @Basic
    @Column(name = "semtask_id")
    public Integer getSemtaskId() {
        return semtaskId;
    }

    public void setSemtaskId(Integer semtaskId) {
        this.semtaskId = semtaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StPrEntity that = (StPrEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
