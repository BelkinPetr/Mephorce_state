package HibernateEntities;

import javax.persistence.*;

@Entity
@Table(name = "regulations")
public class RegulationEntity {

    @Id
    private Long id;

    @Basic
    @Column
    private String text;

    @Basic
    @Column
    private String project;

    @Basic
    @Column
    private String author;

    @Enumerated
    private Status status = Status.APPROVING;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        APPROVED("Утверждено"), DECLINED("Отклонено"), APPROVING("На утверждении");

        Status(String displayName){
            this.displayName = displayName;
        }

        String displayName;

        public String getDisplayName() {
            return displayName;
        }
    }
}
