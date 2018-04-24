package DAO;

import HibernateEntities.ProjectStaffSkillEntity;
import HibernateEntities.SkillCatEntity;
import HibernateEntities.SkillListEntity;

import java.util.List;

/**
 * Created by kinetik on 24.04.17.
 */
public interface SkillDAOInterface {

    static SkillListEntity getSkillById(int id) {
        return null;
    }

    static List<SkillListEntity> getSkillByCat(Integer catId) { return null;}

    static List<ProjectStaffSkillEntity> getProjSkbyStaffId(int staffId) {
        return null;
    }

    static List<SkillCatEntity> getCategorySkill() {
        return null;
    }
}
