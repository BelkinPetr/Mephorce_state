package HibernateEntities;

import javax.persistence.*;


@Entity
@Table(name = "files", schema = "anketa", catalog = "")
public class FilesEntity {
    private int idfiles;
    private String file_name;
    private String file_path;
    private Integer clId;
    private Integer modId;
    private Integer stId;
    private Integer prId;

    @Basic
    @Column(name = "client_id", nullable = false)
    public Integer getClId() {
        return clId;
    }

    public void setClId(Integer clId) {
        this.clId = clId;
    }

    @Basic
    @Column(name = "mod_id", nullable = false)
    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    @Basic
    @Column(name = "st_id", nullable = false)
    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    @Basic
    @Column(name = "pr_id", nullable = false)
    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    @Id
    @Column(name = "idfiles", nullable = false)
    public int getIdfiles() {
        return idfiles;
    }

    public void setIdfiles(int idfiles) {
        this.idfiles = idfiles;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = 255)
    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    @Basic
    @Column(name = "file_path", nullable = true, length = 255)
    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesEntity that = (FilesEntity) o;

        if (idfiles != that.idfiles) return false;
        if (file_name != null ? !file_name.equals(that.file_name) : that.file_name != null) return false;
        if (file_path != null ? !file_path.equals(that.file_path) : that.file_path != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idfiles;
        result = 31 * result + (file_name != null ? file_name.hashCode() : 0);
        result = 31 * result + (file_path != null ? file_path.hashCode() : 0);

        return result;
    }
}

