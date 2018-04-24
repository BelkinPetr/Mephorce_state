package ServiceEntities;

import DAOImplement.TranslationTextDao;
import HibernateEntities.translation.TranslationTextEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationTextService {

    @Autowired
    private TranslationTextDao translationTextDao;

    public void createText(TranslationTextEntity text) {
        translationTextDao.create(text);
    }

    public TranslationTextEntity findTextById(Long id) {
        return translationTextDao.findById(id);
    }

    public void updateText(TranslationTextEntity text) {
        translationTextDao.update(text);
    }

    public void deleteText(TranslationTextEntity text) {
        translationTextDao.delete(text);
    }

    public List<TranslationTextEntity> getUnassignedTexts() {
        return translationTextDao.getUnassignedTexts();
    }

}
