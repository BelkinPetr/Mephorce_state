package HibernateEntities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Nastya Golubeva
 */
@Entity
@Table(name = "community_news", schema = "anketa", catalog = "")
public class NewsEntity {
    private Integer newsId;
    private String creator;
    private Date date;
    private String project;
    private String text;

    private List<CommentEntity> comments;

    @OneToMany
    @JoinColumn(name = "news_id")
    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public NewsEntity(int newsId, String creator, Date date, String project, String text) {
        this.newsId = newsId;
        this.creator = creator;
        this.date = date;
        this.project = project;
        this.text = text;
    }

    public NewsEntity() {

    }

    @Id
    @Column(name = "news_id", nullable = false)
    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "creator", nullable = false)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "date", nullable = false)
    @Type(type = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "project", nullable = false)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Basic
    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsEntity that = (NewsEntity) o;
        return Objects.equals(newsId, that.newsId)
                && (creator != null ? creator.equals(that.creator) : that.creator == null)
                && (date != null ? date.equals(that.date) : that.date == null)
                && (project != null ? project.equals(that.project) : that.project == null)
                && (text != null ? text.equals(that.text) : that.text == null);
    }

    @Override
    public int hashCode() {
        int result = newsId;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
