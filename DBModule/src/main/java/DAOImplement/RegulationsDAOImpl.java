package DAOImplement;

import DAO.RegulationsDAO;
import HibernateEntities.RegulationEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * @author nivanov
 *         on 22.06.17.
 */

@Component
public class RegulationsDAOImpl implements RegulationsDAO {


    @Override
    public void createRegulation(RegulationEntity entity) {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("select max(id) from RegulationEntity");
        Long res = (Long) query.uniqueResult();
        if (res == null) {
            res = 0L;
        }
        entity.setId(res + 1);
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<RegulationEntity> getRegulations() {
        Session session = getSession();
        Query<RegulationEntity> regulationEntityQuery =
                session.createQuery("FROM RegulationEntity", RegulationEntity.class);
        return regulationEntityQuery.list();
    }

    @Override
    public void setStatusById(Long id, RegulationEntity.Status status) {
        Session session = getSession();
        session.beginTransaction();
        Query<RegulationEntity> regulationEntityQuery =
                session.createQuery("FROM RegulationEntity WHERE id = :id", RegulationEntity.class);
        regulationEntityQuery.setParameter("id", id);
        Optional<RegulationEntity> entityOptional = regulationEntityQuery.uniqueResultOptional();
        RegulationEntity entity = entityOptional.orElseThrow(IllegalArgumentException::new);
        entity.setStatus(status);
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeRegulation(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Query<RegulationEntity> regulationEntityQuery =
                session.createQuery("FROM RegulationEntity WHERE id = :id", RegulationEntity.class);
        regulationEntityQuery.setParameter("id", id);
        Optional<RegulationEntity> entityOptional = regulationEntityQuery.uniqueResultOptional();
        RegulationEntity entity = entityOptional.orElseThrow(IllegalArgumentException::new);
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}
