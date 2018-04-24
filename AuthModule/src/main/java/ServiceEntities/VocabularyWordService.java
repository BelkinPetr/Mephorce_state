package ServiceEntities;

import DAOImplement.VocabularyWordDao;
import HibernateEntities.translation.VocabularyWordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VocabularyWordService {

    @Autowired
    private VocabularyWordDao vocabularyWordDao;

    public VocabularyWordEntity createWord(VocabularyWordEntity word) {
        return vocabularyWordDao.create(word);
    }

    public VocabularyWordEntity findById(Long id) {
        return vocabularyWordDao.findById(id);
    }

    public VocabularyWordEntity findByWord(String word) {
        return vocabularyWordDao.findByWord(word);
    }

    public void updateWord(VocabularyWordEntity word) {
        vocabularyWordDao.update(word);
    }

    public void deleteWord(VocabularyWordEntity word) {
        vocabularyWordDao.delete(word);
    }
}
