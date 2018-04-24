package ServiceEntities;


import DAOImplement.ModeratorCommentDao;
import HibernateEntities.translation.ModeratorCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeratorCommentService {

    @Autowired
    private ModeratorCommentDao moderatorCommentDao;

    public void createComment(ModeratorCommentEntity comment) {
        moderatorCommentDao.create(comment);
    }

    public void updateComment(ModeratorCommentEntity comment) {
        moderatorCommentDao.update(comment);
    }

    public void deleteComment(ModeratorCommentEntity comment) {
        moderatorCommentDao.delete(comment);
    }
}
