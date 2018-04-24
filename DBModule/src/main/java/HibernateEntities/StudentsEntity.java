package HibernateEntities;

import javax.persistence.*;

/**
 * Created by kinetik on 27.02.17.
 */
@Entity
@Table(name = "students", schema = "anketa", catalog = "")
public class StudentsEntity {
    private Integer studentsId;
    private String stFamily;
    private String stFirstName;
    private String stSecName;
    private String stPkNumber;
    private Integer stGroup;
    private String stBirthdate = "Нет данных";
    private String stPhone = "Нет данных";
    private String stHometown = "Нет данных";
    private String stEmail = "Нет данных";
    private Integer stFirstIn = 0;

    @Basic
    @Column(name = "first_in", nullable = true)
    public Integer getStFirstIn() {
        return stFirstIn;
    }

    public void setStFirstIn(Integer stFirstIn) {
        this.stFirstIn = stFirstIn;
    }

    @Basic
    @Column(name = "bithdate", nullable = true)
    public String getStBirthdate() {
        return stBirthdate;
    }

    public void setStBirthdate(String stBirthdate) {
        this.stBirthdate = stBirthdate;
    }

    @Basic
    @Column(name = "phone_number", nullable = true)
    public String getStPhone() {
        return stPhone;
    }

    public void setStPhone(String stPhone) {
        this.stPhone = stPhone;
    }

    @Basic
    @Column(name = "hometown", nullable = true)
    public String getStHometown() {
        return stHometown;
    }

    public void setStHometown(String stHometown) {
        this.stHometown = stHometown;
    }

    @Basic
    @Column(name = "e_mail", nullable = true)
    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getStGroup() {
        return stGroup;
    }

    public void setStGroup(Integer stGroup) {
        this.stGroup = stGroup;
    }
    @Id
    @Column(name = "students_id", nullable = false)
    public Integer getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(Integer studentsId) {
        this.studentsId = studentsId;
    }

    @Basic
    @Column(name = "st_family", nullable = true, length = 45)
    public String getStFamily() {
        return stFamily;
    }

    public void setStFamily(String stFamily) {
        this.stFamily = stFamily;
    }

    @Basic
    @Column(name = "st_first_name", nullable = true, length = 45)
    public String getStFirstName() {
        return stFirstName;
    }

    public void setStFirstName(String stFirstName) {
        this.stFirstName = stFirstName;
    }

    @Basic
    @Column(name = "st_sec_name", nullable = true, length = 45)
    public String getStSecName() {
        return stSecName;
    }

    public void setStSecName(String stSecName) {
        this.stSecName = stSecName;
    }

    @Basic
    @Column(name = "st_pk_number", nullable = true, length = 45)
    public String getStPkNumber() {
        return stPkNumber;
    }

    public void setStPkNumber(String stPkNumber) {
        this.stPkNumber = stPkNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntity that = (StudentsEntity) o;

        if (studentsId != that.studentsId) return false;
        if (stFamily != null ? !stFamily.equals(that.stFamily) : that.stFamily != null) return false;
        if (stFirstName != null ? !stFirstName.equals(that.stFirstName) : that.stFirstName != null) return false;
        if (stSecName != null ? !stSecName.equals(that.stSecName) : that.stSecName != null) return false;
        if (stPkNumber != null ? !stPkNumber.equals(that.stPkNumber) : that.stPkNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentsId;
        result = 31 * result + (stFamily != null ? stFamily.hashCode() : 0);
        result = 31 * result + (stFirstName != null ? stFirstName.hashCode() : 0);
        result = 31 * result + (stSecName != null ? stSecName.hashCode() : 0);
        result = 31 * result + (stPkNumber != null ? stPkNumber.hashCode() : 0);
        return result;
    }
}
