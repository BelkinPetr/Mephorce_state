package ServiceEntities;

import DAOImplement.TranslationTopicDao;
import HibernateEntities.translation.TranslationTopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationTopicService {

    @Autowired
    private TranslationTopicDao translationTopicDao;

    public void createTopic(TranslationTopicEntity topic) {
        translationTopicDao.create(topic);
    }

    public TranslationTopicEntity findTopicById(Long id) {
        return translationTopicDao.findById(id);
    }

    public void updateTopic(TranslationTopicEntity topic) {
        translationTopicDao.update(topic);
    }

    public void deleteTopic(TranslationTopicEntity topic) {
        translationTopicDao.delete(topic);
    }

}
