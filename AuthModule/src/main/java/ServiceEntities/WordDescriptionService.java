package ServiceEntities;

import DAOImplement.WordDescriptionDao;
import HibernateEntities.translation.WordDescriptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordDescriptionService {

    @Autowired
    private WordDescriptionDao wordDescriptionDao;

    public WordDescriptionEntity createDescription(WordDescriptionEntity description) {
        return wordDescriptionDao.create(description);
    }

    public void updateDescription(WordDescriptionEntity description) {
        wordDescriptionDao.update(description);
    }

    public void deleteDescription(WordDescriptionEntity description) {
        wordDescriptionDao.delete(description);
    }
}
