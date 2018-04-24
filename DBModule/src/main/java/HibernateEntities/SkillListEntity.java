package HibernateEntities;
import javax.persistence.*;
/**
 * Created by kinetik on 07.03.17.
 */
@Entity
@Table(name = "skil_list", schema = "anketa")
public class SkillListEntity {
    private Integer skilId;
    private String skilName;
    private Integer catId;
    @Id
    @Column(name = "skil_id", nullable = false)
    public Integer getSkilId() {
        return skilId;
    }
    public void setSkilId(Integer skilId) {
        this.skilId = skilId;
    }

    @Basic
    @Column(name = "skil_name", nullable = false)
    public String getSkilName() {
        return skilName;
    }
    public void setSkilName(String skilName) {
        this.skilName = skilName;
    }

    @Basic
    @Column(name = "cat_id")
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
}