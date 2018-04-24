package HibernateEntities.translation;

import javax.persistence.*;

@Entity
@Table(name = "translation_topics", schema = "anketa")
public class TranslationTopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "topic", nullable = false)
    private String topic;

    public TranslationTopicEntity() {
    }

    public TranslationTopicEntity(String topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
