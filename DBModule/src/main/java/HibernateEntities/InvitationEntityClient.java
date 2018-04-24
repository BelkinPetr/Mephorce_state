package HibernateEntities;

import javax.persistence.*;

@Entity
@Table(name = "invitations_client", schema = "anketa")
public class InvitationEntityClient {
    private String id;
    private Integer prId;
    private Integer modId;
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
        this.id = Integer.toString(this.prId) + "_" + Integer.toString(this.modId);
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
    @Column(name = "mod_id")
    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
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

        InvitationEntityClient that = (InvitationEntityClient) o;

        if (!id.equals(that.id)) return false;
        if (prId != null ? !prId.equals(that.prId) : that.prId != null) return false;
        return modId != null ? modId.equals(that.modId) : that.modId == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (prId != null ? prId.hashCode() : 0);
        result = 31 * result + (modId != null ? modId.hashCode() : 0);
        return result;
    }
}
