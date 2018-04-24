package HibernateEntities;

import javax.persistence.*;

/**
 * Created by kinetik on 24.05.17.
 */
@Entity
@Table(name = "invitations", schema = "anketa")
public class InvitationEntity {
    private String id;
    private Integer prId;
    private Integer stId;
    private Integer result;

    @Id
    @Column(name = "match_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId(){
        this.id = Integer.toString(this.prId) + "_" + Integer.toString(this.stId);
    }

    @Basic
    @Column(name = "proj_id")
    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    @Basic
    @Column(name = "stud_id")
    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    @Basic
    @Column(name = "result")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvitationEntity that = (InvitationEntity) o;

        if (!id.equals(that.id)) return false;
        if (prId != null ? !prId.equals(that.prId) : that.prId != null) return false;
        return stId != null ? stId.equals(that.stId) : that.stId == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (prId != null ? prId.hashCode() : 0);
        result = 31 * result + (stId != null ? stId.hashCode() : 0);
        return result;
    }
}
