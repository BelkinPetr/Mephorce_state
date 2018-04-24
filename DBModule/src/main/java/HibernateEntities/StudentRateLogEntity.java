package HibernateEntities;

import javax.persistence.*;

/**
 * Created by ilysko on 01.07.17.
 */
@Entity
@Table(name = "students_rate_log", schema = "anketa")
public class StudentRateLogEntity {
    private int id;
    private int studentId;
    private int moderatorId;
    private int skillCatId;
    private int projectId;
    private String comment;

    @Id
    @Column(name = "students_rate_log_id")
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
    @Column(name = "mod_id")
    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
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
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
