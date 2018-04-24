package HibernateEntities;

import javax.persistence.*;
/**
 * Created by kinetik on 07.03.17.
 */
@Entity
@Table(name = "skil_match", schema = "anketa")
public class MatchSkilEntity {
    private Integer catId;
    private Integer skilId;
    private String matchId;
    private String skillName;

    @Basic
    @Column(name = "skill_name", nullable = false)
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Basic
    @Column(name = "skil_cat_id", nullable = false)
    public Integer getCatId() {
        return catId;
    }
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "skil_id", nullable = false)
    public Integer getSkilId() {
        return skilId;
    }
    public void setSkilId(Integer skilId) {
        this.skilId = skilId;
    }

    @Id
    @Column(name = "match_id", nullable = false)
    public String getMatchId() {
        return matchId;
    }
    public void setMatchId(){
        this.matchId = String.valueOf(this.getSkilId())+"_"+String.valueOf(this.getCatId());
    }
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}