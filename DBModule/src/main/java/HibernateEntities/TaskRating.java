package HibernateEntities;

import javax.persistence.*;

/**
 * Created by ilysko on 29.06.17.
 */
@Entity
@Table(name = "st_mod_task_rating", schema = "anketa", catalog = "")
public class TaskRating {
    private int id;
    private int studentId;
    private int skillCatId;
    private int taskId;
    private int moderatorId;
    private int sendToModeratorAmount;
    private int returnedByModeratorAmount;
    private boolean acceptByModerator;
    private int taskWorkRate;
    private boolean abandonment;

    @Id
    @Column(name = "st_mod_task_rating_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "students_id")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "skil_cat_id")
    public int getSkillCatId() {
        return skillCatId;
    }

    public void setSkillCatId(int skillCatId) {
        this.skillCatId = skillCatId;
    }

    @Basic
    @Column(name = "task_id")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "mod_id")
    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    @Basic
    @Column(name = "send_to_mod_amount")
    public int getSendToModeratorAmount() {
        return sendToModeratorAmount;
    }

    public void setSendToModeratorAmount(int sendToModeratorAmount) {
        this.sendToModeratorAmount = sendToModeratorAmount;
    }

    @Basic
    @Column(name = "returned_by_mod_amount")
    public int getReturnedByModeratorAmount() {
        return returnedByModeratorAmount;
    }

    public void setReturnedByModeratorAmount(int returnedByModeratorAmount) {
        this.returnedByModeratorAmount = returnedByModeratorAmount;
    }

    @Basic
    @Column(name = "accept_by_mod")
    public boolean isAcceptByModerator() {
        return acceptByModerator;
    }

    public void setAcceptByModerator(boolean acceptByModerator) {
        this.acceptByModerator = acceptByModerator;
    }

    @Basic
    @Column(name = "task_work_rate")
    public int getTaskWorkRate() {
        return taskWorkRate;
    }

    public void setTaskWorkRate(int taskWorkRate) {
        this.taskWorkRate = taskWorkRate;
    }

    @Basic
    @Column(name = "abandonment")
    public boolean isAbandonment() {
        return abandonment;
    }

    public void setAbandonment(boolean abandonment) {
        this.abandonment = abandonment;
    }
}
