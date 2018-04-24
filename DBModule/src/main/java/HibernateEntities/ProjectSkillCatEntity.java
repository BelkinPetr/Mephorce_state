package HibernateEntities;
import javax.persistence.*;
/**
 * Created by kinetik on 07.03.17.
 */
@Entity
@Table(name = "project_skil_cat", schema = "anketa")
public class ProjectSkillCatEntity {
    private Integer prId;
    private Integer skilCatId;
    private String pcsId;
    @Id
    @Column(name = "pcs_id", nullable = false)
    public String getPcsId() {
        return pcsId;
    }
    public void setPcsId(String pcsId) {
        this.pcsId = pcsId;
    }
    public void setPcsId(){
        this.pcsId = String.valueOf(this.getPrId())+" "+String.valueOf(this.getSkilCatId());
    }
    @Basic
    @Column(name = "project_id", nullable = false)
    public Integer getPrId() {
        return prId;
    }
    public void setPrId(Integer prId) {
        this.prId = prId;
    }
    @Basic
    @Column(name = "skil_cat_id", nullable = false)
    public Integer getSkilCatId() {
        return skilCatId;
    }
    public void setSkilCatId(Integer skilCatId) {
        this.skilCatId = skilCatId;
    }
}
