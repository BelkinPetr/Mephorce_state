package HibernateEntities.translation;

import HibernateEntities.StudentsEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "descriptions", schema = "anketa")
public class WordDescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private VocabularyWordEntity vocabularyWord;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "topic_id")
    private TranslationTopicEntity topic;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private StudentsEntity sender;

    @Column(name = "rating")
    private Integer rating = 0;

    @Enumerated(EnumType.STRING)
    private ModerationStatus status = ModerationStatus.AWAITING_MODERATION;

    public WordDescriptionEntity() {
    }

    public WordDescriptionEntity(VocabularyWordEntity vocabularyWord, String description, TranslationTopicEntity topic, StudentsEntity sender) {
        this.vocabularyWord = vocabularyWord;
        this.description = description;
        this.topic = topic;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public VocabularyWordEntity getVocabularyWord() {
        return vocabularyWord;
    }

    public String getDescription() {
        return description;
    }

    public TranslationTopicEntity getTopic() {
        return topic;
    }

    public StudentsEntity getSender() {
        return sender;
    }

    public Integer getRating() {
        return rating;
    }

    public ModerationStatus getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(ModerationStatus status) {
        this.status = status;
    }

    public void increaseRating() {
        rating++;
    }
}
