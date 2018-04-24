package HibernateEntities.translation;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "vocabulary_words", schema = "anketa")
public class VocabularyWordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "word", nullable = false)
    private String word;

    @OneToMany(mappedBy = "vocabularyWord")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<WordTranslationEntity> translations = new ArrayList<>();

    @OneToMany(mappedBy = "vocabularyWord")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<WordDescriptionEntity> descriptions = new ArrayList<>();

    public VocabularyWordEntity() {
    }

    public VocabularyWordEntity(String word, List<WordTranslationEntity> translations, List<WordDescriptionEntity> descriptions) {
        this.word = word;
        this.translations = translations;
        this.descriptions = descriptions;
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public List<WordTranslationEntity> getTranslations() {
        return translations;
    }

    public List<WordDescriptionEntity> getDescriptions() {
        return descriptions;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<WordTranslationEntity> getTranslationsByTopic(TranslationTopicEntity topic) {
        return translations.stream()
                .filter(trans -> Objects.equals(trans.getTopic().getId(), topic.getId()))
                .collect(Collectors.toList());
    }

    public List<WordDescriptionEntity> getDescriptionsByTopic(TranslationTopicEntity topic) {
        return descriptions.stream()
                .filter(desc -> Objects.equals(desc.getTopic().getId(), topic.getId()))
                .collect(Collectors.toList());
    }


}
