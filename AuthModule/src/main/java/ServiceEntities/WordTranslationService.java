package ServiceEntities;

import DAOImplement.WordTranslationDao;
import HibernateEntities.translation.WordTranslationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordTranslationService {

    @Autowired
    private WordTranslationDao wordTranslationDao;

    public WordTranslationEntity createTranslation(WordTranslationEntity translation) {
        return wordTranslationDao.create(translation);
    }

    public void updateTranslation(WordTranslationEntity translation) {
        wordTranslationDao.update(translation);
    }

    public void deleteTranslation(WordTranslationEntity translation) {
        wordTranslationDao.delete(translation);
    }
}
