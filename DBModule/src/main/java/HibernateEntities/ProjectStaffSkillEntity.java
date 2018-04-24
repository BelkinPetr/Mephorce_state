package HibernateEntities;
import javax.persistence.*;
/**
 * Created by kinetik on 07.03.17.
 */
@Entity
@Table(name = "project_mod_skill", schema = "anketa")
public class ProjectStaffSkillEntity {

    private Integer projId;
    private Integer skillId;
    private Integer slaveId;
    private Integer slRating;
    private String pmsId;
    private String skillName;

    @Id
    @Column(name = "pms_id", nullable = false)
    public String getPmsId() {
        return pmsId;
    }
    public void setPmsId(String pmsId) {
        this.pmsId = pmsId;
    }
    public void setPmsId() {
        this.pmsId = String.valueOf(this.getProjId())+"_"+String.valueOf(this.getSkillId())+"_"+String.valueOf(this.getSlaveId());
    }

    @Basic
    @Column(name = "project_id", nullable = false)
    public Integer getProjId() {
        return projId;
    }
    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    @Basic
    @Column(name = "skil_id", nullable = false)
    public Integer getSkillId() {
        return skillId;
    }
    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "slave_id", nullable = false)
    public Integer getSlaveId() {
        return slaveId;
    }
    public void setSlaveId(Integer slaveId) {
        this.slaveId = slaveId;
    }

    @Basic
    @Column(name = "skill_name", nullable = false)
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public Integer getSlRating() {
        return slRating;
    }
    public void setSlRating(Integer slRating) {
        this.slRating = slRating;
    }
}