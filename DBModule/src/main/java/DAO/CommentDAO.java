package DAO;

import HibernateEntities.CommentEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author nivanov
 *         on 22.06.17.
 */
public interface CommentDAO {
    void createComment(CommentEntity commentEntity);
    Optional<CommentEntity> findById(Long id);
}
