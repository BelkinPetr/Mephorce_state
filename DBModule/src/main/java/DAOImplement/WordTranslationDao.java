package DAOImplement;

import HibernateEntities.translation.WordTranslationEntity;
import org.springframework.stereotype.Component;

@Component
public class WordTranslationDao extends GenericDao<WordTranslationEntity> {
    public WordTranslationDao() {
        super(WordTranslationEntity.class);
    }
}
