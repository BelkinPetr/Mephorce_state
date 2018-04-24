package HibernateEntities;

import javax.persistence.*;


@Entity
@Table(name = "photos", schema = "anketa", catalog = "")
public class PhotosEntity {
    private Integer id_photo;


    private String photo_name;
    private String photo_path;
    private Integer clId;
    private Integer modId;
    private Integer stId;
   

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

   
    @Id
    @Column(name = "id_photo", nullable = false)
    public int getId_photo() {
        return id_photo;
    }

    public void setId_photo(int id_photo) {
        this.id_photo = id_photo;
    }

    @Basic
    @Column(name = "photo_name", nullable = true, length = 255)
    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    @Basic
    @Column(name = "photo_path", nullable = true, length = 255)
    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotosEntity that = (PhotosEntity) o;

        if (id_photo != that.id_photo) return false;
        if (photo_name != null ? !photo_name.equals(that.photo_name) : that.photo_name != null) return false;
        if (photo_path != null ? !photo_path.equals(that.photo_path) : that.photo_path != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id_photo;
        result = 31 * result + (photo_name != null ? photo_name.hashCode() : 0);
        result = 31 * result + (photo_path != null ? photo_path.hashCode() : 0);

        return result;
    }
}
