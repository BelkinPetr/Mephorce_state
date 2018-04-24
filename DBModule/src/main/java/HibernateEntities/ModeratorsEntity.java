package HibernateEntities;

import javax.persistence.*;

/**
 * Created by kinetik on 27.02.17.
 */
@Entity
@Table(name = "moderators", schema = "anketa", catalog = "")
public class ModeratorsEntity {
    private Integer modId;
    private String modFamily;
    private String modFirstName;
    private String modSecName;
    private String modPassword;
    private String modBirthdate = "Нет данных";
    private String modEmail = "Нет данных";
    private String modHometown = "Нет данных";
    private String modPhone = "Нет данных";
    private Integer modFirstIn = 0;

    @Basic
    @Column(name = "first_in", nullable = true)
    public Integer getModFirstIn() {
        return modFirstIn;
    }

    public void setModFirstIn(Integer modFirstIn) {
        this.modFirstIn = modFirstIn;
    }

    @Id
    @Column(name = "mod_id", nullable = false)
    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    @Basic
    @Column(name = "mod_family", nullable = true, length = 45)
    public String getModFamily() {
        return modFamily;
    }

    public void setModFamily(String modFamily) {
        this.modFamily = modFamily;
    }

    @Basic
    @Column(name = "mod_first_name", nullable = true, length = 45)
    public String getModFirstName() {
        return modFirstName;
    }

    public void setModFirstName(String modFirstName) {
        this.modFirstName = modFirstName;
    }

    @Basic
    @Column(name = "mod_sec_name", nullable = true, length = 45)
    public String getModSecName() {
        return modSecName;
    }

    public void setModSecName(String modSecName) {
        this.modSecName = modSecName;
    }

    @Basic
    @Column(name = "mod_password", nullable = true, length = 45)
    public String getModPassword() {
        return modPassword;
    }

    public void setModPassword(String modPassword) {
        this.modPassword = modPassword;
    }

    @Basic
    @Column(name = "mod_birthdate", nullable = true, length = 255)
    public String getModBirthdate() {
        return modBirthdate;
    }

    public void setModBirthdate(String modBirthdate) {
        this.modBirthdate = modBirthdate;
    }

    @Basic
    @Column(name = "mod_email", nullable = true, length = 255)
    public String getModEmail() {
        return modEmail;
    }

    public void setModEmail(String modEmail) {
        this.modEmail = modEmail;
    }

    @Basic
    @Column(name = "mod_hometown", nullable = true, length = 255)
    public String getModHometown() {
        return modHometown;
    }

    public void setModHometown(String modHometown) {
        this.modHometown = modHometown;
    }

    @Basic
    @Column(name = "mod_phone", nullable = true, length = 255)
    public String getModPhone() {
        return modPhone;
    }

    public void setModPhone(String modPhone) {
        this.modPhone = modPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModeratorsEntity that = (ModeratorsEntity) o;

        if (modId != that.modId) return false;
        if (modFamily != null ? !modFamily.equals(that.modFamily) : that.modFamily != null) return false;
        if (modFirstName != null ? !modFirstName.equals(that.modFirstName) : that.modFirstName != null) return false;
        if (modSecName != null ? !modSecName.equals(that.modSecName) : that.modSecName != null) return false;
        if (modPassword != null ? !modPassword.equals(that.modPassword) : that.modPassword != null) return false;
        if (modBirthdate != null ? !modBirthdate.equals(that.modBirthdate) : that.modBirthdate != null) return false;
        if (modEmail != null ? !modEmail.equals(that.modEmail) : that.modEmail != null) return false;
        if (modHometown != null ? !modHometown.equals(that.modHometown) : that.modHometown != null) return false;
        if (modPhone != null ? !modPhone.equals(that.modPhone) : that.modPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = modId;
        result = 31 * result + (modFamily != null ? modFamily.hashCode() : 0);
        result = 31 * result + (modFirstName != null ? modFirstName.hashCode() : 0);
        result = 31 * result + (modSecName != null ? modSecName.hashCode() : 0);
        result = 31 * result + (modPassword != null ? modPassword.hashCode() : 0);
        result = 31 * result + (modBirthdate != null ? modBirthdate.hashCode() : 0);
        result = 31 * result + (modEmail != null ? modEmail.hashCode() : 0);
        result = 31 * result + (modHometown != null ? modHometown.hashCode() : 0);
        result = 31 * result + (modPhone != null ? modPhone.hashCode() : 0);
        return result;
    }
}
