package HibernateEntities.translation;

import HibernateEntities.StudentsEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "translations", schema = "anketa")
public class WordTranslationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private VocabularyWordEntity vocabularyWord;

    @Column(name = "translation", nullable = false)
    private String translation;

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

    public WordTranslationEntity() {
    }

    public WordTranslationEntity(VocabularyWordEntity vocabularyWord, String translation, TranslationTopicEntity topic, StudentsEntity sender) {
        this.vocabularyWord = vocabularyWord;
        this.translation = translation;
        this.topic = topic;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public VocabularyWordEntity getVocabularyWord() {
        return vocabularyWord;
    }

    public String getTranslation() {
        return translation;
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

    public void setVocabularyWord(VocabularyWordEntity vocabularyWord) {
        this.vocabularyWord = vocabularyWord;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setTopic(TranslationTopicEntity topic) {
        this.topic = topic;
    }

    public void setStatus(ModerationStatus status) {
        this.status = status;
    }

    public void increaseRating() {
        rating++;
    }

}
