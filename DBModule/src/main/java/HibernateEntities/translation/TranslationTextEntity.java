package HibernateEntities.translation;

import HibernateEntities.StudentsEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "translation_texts", schema = "anketa")
public class TranslationTextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "initial_text")
    private String initialText;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private StudentsEntity student;

    @Column(name = "result_text")
    private String resultText;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNASSIGNED;

    @Column(name = "full_text")
    private String fullText;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "translatedText", orphanRemoval = true)
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<ModeratorCommentEntity> moderatorComments = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "standard_id")
    private StandardsEntity standards;

    public TranslationTextEntity() {
    }

    public TranslationTextEntity(String initialText, String resultText, String fullText, StandardsEntity standards) {
        this.initialText = initialText;
        this.resultText = resultText;
        this.fullText = fullText;
        this.standards = standards;
    }

    public Long getId() {
        return id;
    }

    public String getInitialText() {
        return initialText;
    }

    public StudentsEntity getStudent() {
        return student;
    }

    public String getResultText() {
        return resultText;
    }

    public Status getStatus() {
        return status;
    }

    public String getFullText() {
        return fullText;
    }

    public List<ModeratorCommentEntity> getModeratorComments() {
        return moderatorComments;
    }

    public StandardsEntity getStandards() {
        return standards;
    }

    public void addModeratorComment(ModeratorCommentEntity moderatorCommentEntity) {
        getModeratorComments().add(moderatorCommentEntity);
        moderatorCommentEntity.setTranslatedText(this);
    }

    public void removeModeratorComment(ModeratorCommentEntity moderatorCommentEntity) {
        moderatorCommentEntity.setTranslatedText(null);
        this.moderatorComments.remove(moderatorCommentEntity);
    }

    public void setInitialText(String initialText) {
        this.initialText = initialText;
    }

    public void setStudent(StudentsEntity student) {
        this.student = student;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setStandards(StandardsEntity standards) {
        this.standards = standards;
    }

    public enum Status {
        UNASSIGNED, UNCOMPLETED, NEEDS_REVIEW, COMPLETED, PARTIALY_COMPLETED, NEEDS_CORRECTION
    }

}
