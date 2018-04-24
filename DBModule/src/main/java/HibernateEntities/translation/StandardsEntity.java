package HibernateEntities.translation;

import javax.persistence.*;

@Entity
@Table(name = "standards", schema = "anketa")
public class StandardsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "standards")
    private String standards;

    public StandardsEntity() {
    }

    public StandardsEntity(String standards) {
        this.standards = standards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStandarts() {
        return standards;
    }

    public void setStandarts(String standarts) {
        this.standards = standarts;
    }
}
