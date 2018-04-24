package HibernateEntities.translation;

import HibernateEntities.ModeratorsEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "moderator_comments", schema = "anketa")
public class ModeratorCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "text_id", nullable = false)
    private TranslationTextEntity translatedText;

    @ManyToOne
    @JoinColumn(name = "moderator_id", nullable = false)
    private ModeratorsEntity moderator;

    @Column(name = "created_at")
    private Timestamp createdAt = Timestamp.from(Instant.now());

    public ModeratorCommentEntity() {
    }

    public ModeratorCommentEntity(String comment, TranslationTextEntity translatedText, ModeratorsEntity moderator) {
        this.comment = comment;
        this.translatedText = translatedText;
        this.moderator = moderator;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public TranslationTextEntity getTranslatedText() {
        return translatedText;
    }

    public ModeratorsEntity getModerator() {
        return moderator;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTranslatedText(TranslationTextEntity translatedText) {
        this.translatedText = translatedText;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
