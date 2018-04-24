package DAO;

import HibernateEntities.RegulationEntity;

import java.util.List;

/**
 * @author nivanov
 *         on 22.06.17.
 */
public interface RegulationsDAO {
    void createRegulation(RegulationEntity entity);
    List<RegulationEntity> getRegulations();
    void setStatusById(Long id, RegulationEntity.Status status);
    void removeRegulation(Long id);
}
