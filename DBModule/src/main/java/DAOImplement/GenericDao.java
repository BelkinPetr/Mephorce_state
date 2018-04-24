package DAOImplement;

import DAO.GenericDaoInterface;
import org.hibernate.Session;

import static HibernateUtil.HibernateUtil.getSession;

abstract class GenericDao<T> implements GenericDaoInterface<T> {

    private Class<T> type;

    GenericDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public T create(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    @Override
    public T findById(Long id) {
        return getSession().get(type, id);
    }

    @Override
    public void update(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}
