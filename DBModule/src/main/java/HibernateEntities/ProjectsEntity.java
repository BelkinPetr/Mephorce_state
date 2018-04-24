package HibernateEntities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Veronika on 27.02.17.
 */
@Entity
@Table(name = "projects", schema = "anketa", catalog = "")
public class ProjectsEntity {
    private int prId;
    private String title;
    private String description;
    private Date dateOfCreation;
    private String status;
    private Date dateOfReady;
    private Integer sum;
    private String jsondata;
    private Integer clId;
    private Integer mdId;
    private Integer moneyDivision;
    private Integer personNumber;
    private String worktypes;

    @Basic
    @Column(name = "client_id", nullable = false)
    public Integer getClId() {
        return clId;
    }

    public void setClId(Integer clId) {
        this.clId = clId;
    }

    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mod_id", nullable = false)
    public Integer getMdId() {
        return mdId;
    }

    public void setMdId(Integer mdid) {this.mdId=mdid;}

    @Id
    @Column(name = "pr_id", nullable = false)
    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "date_of_creation", nullable = false,columnDefinition = "2017-02-17")
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "date_of_ready", nullable = true)
    public Date getDateOfReady() {
        return dateOfReady;
    }

    public void setDateOfReady(Date dateOfReady) {
        this.dateOfReady = dateOfReady;
    }

    @Basic
    @Column(name = "sum", nullable = true)
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "jsondata", nullable = true, length = 255)
    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    @Basic
    @Column(name = "moneyDiv")
    public Integer getMoneyDivision() {
        return moneyDivision;
    }

    public void setMoneyDivision(Integer moneyDivision) {
        this.moneyDivision = moneyDivision;
    }

    @Basic
    @Column(name = "personNumber")
    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    @Basic
    @Column(name = "worktypes")
    public String getWorktypes(){ return  worktypes;}
    public  void setWorktypes(String worktypes) {this.worktypes = worktypes;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectsEntity that = (ProjectsEntity) o;

        if (prId != that.prId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dateOfCreation != null ? !dateOfCreation.equals(that.dateOfCreation) : that.dateOfCreation != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (dateOfReady != null ? !dateOfReady.equals(that.dateOfReady) : that.dateOfReady != null) return false;
        if (sum != null ? !sum.equals(that.sum) : that.sum != null) return false;
        if (jsondata != null ? !jsondata.equals(that.jsondata) : that.jsondata != null) return false;
        if (worktypes != null ? !worktypes.equals(that.worktypes) : that.worktypes !=null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = prId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateOfReady != null ? dateOfReady.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (jsondata != null ? jsondata.hashCode() : 0);
        result = 31 * result + (worktypes !=null ? worktypes.hashCode() : 0);
        return result;
    }
}