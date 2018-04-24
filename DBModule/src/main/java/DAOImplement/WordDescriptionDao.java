package DAOImplement;

import HibernateEntities.translation.WordDescriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class WordDescriptionDao extends GenericDao<WordDescriptionEntity> {
    public WordDescriptionDao() {
        super(WordDescriptionEntity.class);
    }
}
