package HibernateEntities;


import javax.persistence.*;

/**
 * Created by kinetik on 27.02.17.
 */
@Entity
@Table(name = "clients", schema = "anketa", catalog = "")
public class ClientsEntity {
    private Integer clientId;
    private String clFamily;
    private String clFirstName;
    private String clSecName;
    private String clPassword;
    private String clPhone = "Нет данных";
    private String clEmail = "Нет данных";
    private String clCompany = "Нет данных";
    private String clPos = "Нет данных";
    private String clHometown = "Нет данных";
    private String clBirthdate = "Нет данных";
    private Integer clFirstIn = 0;

    @Basic
    @Column(name = "first_in", nullable = true)
    public Integer getClFirstIn() {return clFirstIn;}

    public void setClFirstIn(Integer clFirstIn) { this.clFirstIn = clFirstIn;}

    @Basic
    @Column(name = "phone_number", nullable = true)
    public String getClPhone() {
        return clPhone;
    }

    public void setClPhone(String clPhone) {
        this.clPhone = clPhone;
    }

    @Basic
    @Column(name = "e_mail", nullable = true)
    public String getClEmail() {
        return clEmail;
    }

    public void setClEmail(String clEmail) {
        this.clEmail = clEmail;
    }

    @Basic
    @Column(name = "company", nullable = true)
    public String getClCompany() {
        return clCompany;
    }

    public void setClCompany(String clCompany) {
        this.clCompany = clCompany;
    }

    @Basic
    @Column(name = "pos", nullable = true)
    public String getClPos() {
        return clPos;
    }

    public void setClPos(String clPos) {
        this.clPos = clPos;
    }

    @Basic
    @Column(name = "hometown", nullable = true)
    public String getClHometown() {
        return clHometown;
    }

    public void setClHometown(String clHometown) {
        this.clHometown = clHometown;
    }

    @Basic
    @Column(name = "birthdate", nullable = true)
    public String getClBirthdate() {
        return clBirthdate;
    }

    public void setClBirthdate(String clBirthdate) {
        this.clBirthdate = clBirthdate;
    }

    @Id
    @Column(name = "client_id", nullable = false)
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "cl_family", nullable = true, length = 45)
    public String getClFamily() {
        return clFamily;
    }

    public void setClFamily(String clFamily) {
        this.clFamily = clFamily;
    }

    @Basic
    @Column(name = "cl_first_name", nullable = true, length = 45)
    public String getClFirstName() {
        return clFirstName;
    }

    public void setClFirstName(String clFirstName) {
        this.clFirstName = clFirstName;
    }

    @Basic
    @Column(name = "cl_sec_name", nullable = true, length = 45)
    public String getClSecName() {
        return clSecName;
    }

    public void setClSecName(String clSecName) {
        this.clSecName = clSecName;
    }

    @Basic
    @Column(name = "cl_password", nullable = true, length = 45)
    public String getClPassword() {
        return clPassword;
    }

    public void setClPassword(String clPassword) {
        this.clPassword = clPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientsEntity that = (ClientsEntity) o;

        if (clientId != that.clientId) return false;
        if (clFamily != null ? !clFamily.equals(that.clFamily) : that.clFamily != null) return false;
        if (clFirstName != null ? !clFirstName.equals(that.clFirstName) : that.clFirstName != null) return false;
        if (clSecName != null ? !clSecName.equals(that.clSecName) : that.clSecName != null) return false;
        if (clPassword != null ? !clPassword.equals(that.clPassword) : that.clPassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (clFamily != null ? clFamily.hashCode() : 0);
        result = 31 * result + (clFirstName != null ? clFirstName.hashCode() : 0);
        result = 31 * result + (clSecName != null ? clSecName.hashCode() : 0);
        result = 31 * result + (clPassword != null ? clPassword.hashCode() : 0);
        return result;
    }

    public String peekFIO() {
        return new StringBuilder(clFamily).append(" ").append(clFirstName).append(" ").append(clSecName).toString();
    }
}