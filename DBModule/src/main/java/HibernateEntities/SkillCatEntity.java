package HibernateEntities;
import javax.persistence.*;
/**
 * Created by kinetik on 07.03.17.
 */
@Entity
@Table(name = "skil_cat", schema = "anketa")
public class SkillCatEntity {
    private Integer catId;
    private String catName;
    @Id
    @Column(name = "skil_cat_id", nullable = false)
    public Integer getCatId() {
        return catId;
    }
    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    @Basic
    @Column(name = "skil_cat_name", nullable = false)
    public String getCatName() {
        return catName;
    }
    public void setCatName(String catName) {
        this.catName = catName;
    }
}

