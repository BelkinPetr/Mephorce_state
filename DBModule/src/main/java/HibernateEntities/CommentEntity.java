package HibernateEntities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    private Long id;

    @Basic
    @Column
    private String author;

    @Basic
    @Column
    private String text;

    @Column(name = "creation_time")
    private Date creationTime;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private NewsEntity newsEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentEntity)) return false;

        CommentEntity comment = (CommentEntity) o;

        if (getId() != null ? !getId().equals(comment.getId()) : comment.getId() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(comment.getAuthor()) : comment.getAuthor() != null) return false;
        if (getText() != null ? !getText().equals(comment.getText()) : comment.getText() != null) return false;
        return getCreationTime() != null ? getCreationTime().equals(comment.getCreationTime()) : comment.getCreationTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getCreationTime() != null ? getCreationTime().hashCode() : 0);
        return result;
    }

    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public void setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", creationTime=" + creationTime +
                ", newsEntity=" + newsEntity +
                '}';
    }
}
