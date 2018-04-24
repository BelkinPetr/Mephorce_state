package DAOImplement;

import HibernateEntities.translation.ModeratorCommentEntity;
import org.springframework.stereotype.Component;

@Component
public class ModeratorCommentDao extends GenericDao<ModeratorCommentEntity> {

    public ModeratorCommentDao() {
        super(ModeratorCommentEntity.class);
    }
}
