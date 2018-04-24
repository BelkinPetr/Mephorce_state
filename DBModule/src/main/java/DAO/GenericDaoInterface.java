package DAO;

public interface GenericDaoInterface<T> {
    T create(T entity);

    T findById(Long id);

    void update(T entity);

    void delete(T entity);
}
