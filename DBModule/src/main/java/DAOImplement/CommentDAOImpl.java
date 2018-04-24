package DAOImplement;

import DAO.CommentDAO;
import HibernateEntities.CommentEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * @author nivanov
 *         on 22.06.17.
 */

@Component
public class CommentDAOImpl implements CommentDAO {


    @Override
    public void createComment(CommentEntity commentEntity) {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("select max(id) from CommentEntity ");
        Long res = (Long) query.uniqueResult();
        if (res == null) {
            res = 0L;
        }
        commentEntity.setId(res + 1);
        session.save(commentEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<CommentEntity> findById(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Query<CommentEntity> commentEntityQuery = session.createQuery("FROM CommentEntity WHERE id = :id",
                CommentEntity.class);
        commentEntityQuery.setParameter("id", id);
        return commentEntityQuery.uniqueResultOptional();
    }
}
