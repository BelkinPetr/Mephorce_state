package DAOImplement;

import HibernateEntities.translation.TranslationTopicEntity;
import org.springframework.stereotype.Component;

@Component
public class TranslationTopicDao extends GenericDao<TranslationTopicEntity> {

    public TranslationTopicDao() {
        super(TranslationTopicEntity.class);
    }

}
