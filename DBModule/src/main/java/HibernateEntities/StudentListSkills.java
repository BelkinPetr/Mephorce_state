package HibernateEntities;

import javax.persistence.*;

/**
 * Created by kinetik on 08.03.17.
 */
@Entity
@Table(name = "students_skill", schema = "anketa")
public class StudentListSkills {
    private String pairId;
    private Integer studentId;
    private Integer skillId;
    private Integer rating;

    @Id
    @Column(name = "st_sk_id", nullable = false)
    public String getPairId() {
        return pairId;
    }

    public void setPairId(String pairId) {
        this.pairId = pairId;
    }

    public void setPairId() {
        this.pairId = String.valueOf(this.studentId) + "_" + String.valueOf(this.skillId);
    }

    @Basic
    @Column(name = "stud_id", nullable = false)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "skill_id", nullable = false)
    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
