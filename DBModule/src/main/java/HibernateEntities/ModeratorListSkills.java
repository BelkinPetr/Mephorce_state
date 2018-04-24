package HibernateEntities;

import javax.persistence.*;

/**
 * Created by kinetik on 08.03.17.
 */
@Entity
@Table(name = "mod_skill", schema = "anketa")
public class ModeratorListSkills {
    private String pairId;
    private Integer skillId;
    private Integer modId;
    private Integer rating;

    @Id
    @Column(name = "mod_sk_id" , nullable = false)
    public String getPairId() {
        return pairId;
    }

    public void setPairId(String pairId) {
        this.pairId = pairId;
    }

    public void setPairId() {
        this.pairId = String.valueOf(this.modId) + "_" + String.valueOf(this.skillId);
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
    @Column(name = "mod_id", nullable = false)
    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
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
