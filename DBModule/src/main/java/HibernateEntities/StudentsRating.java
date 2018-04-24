package HibernateEntities;

import javax.persistence.*;

/**
 * Created by ilysko on 28.06.17.
 */
@Entity
@Table(name = "students_rating", schema = "anketa")
public class StudentsRating {
    private int studentRatingId;
    private int studentId;
    private int skillCatId;
    private int informalPlusAmount;
    private int informalMinusAmount;
    private int informalZeroAmount;
    private float formalRating;

    @Id
    @Column(name = "students_rating_id")
    public int getStudentRatingId() {
        return studentRatingId;
    }

    public void setStudentRatingId(int studentRatingId) {
        this.studentRatingId = studentRatingId;
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
    @Column(name = "informal_plus_amount")
    public int getInformalPlusAmount() {
        return informalPlusAmount;
    }

    public void setInformalPlusAmount(int informalPlusAmount) {
        this.informalPlusAmount = informalPlusAmount;
    }

    @Basic
    @Column(name = "informal_minus_amount")
    public int getInformalMinusAmount() {
        return informalMinusAmount;
    }

    public void setInformalMinusAmount(int informalMinusAmount) {
        this.informalMinusAmount = informalMinusAmount;
    }

    @Basic
    @Column(name = "informal_zero_amount")
    public int getInformalZeroAmount() {
        return informalZeroAmount;
    }

    public void setInformalZeroAmount(int informalZeroAmount) {
        this.informalZeroAmount = informalZeroAmount;
    }

    @Basic
    @Column(name = "formal_rating")
    public float getFormalRating() {
        return formalRating;
    }

    public void setFormalRating(float formalRating) {
        this.formalRating = formalRating;
    }
}
