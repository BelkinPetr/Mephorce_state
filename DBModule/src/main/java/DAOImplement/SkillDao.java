package DAOImplement;

import DAO.SkillDAOInterface;
import HibernateEntities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import services.UserSkill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by kinetik on 08.03.17.
 */
public class SkillDao implements SkillDAOInterface {

    public static SkillListEntity getSkillById(int id) {
        Session session = getSession();
        Query query = session.createQuery("From SkillListEntity where skilId = :skillId");
        query.setParameter("skillId", id);
        List<SkillListEntity> res = query.list();
        session.close();
        return res.get(0);
    }

    public static HashMap<String, UserSkill> getSkillsById(int stId) {
        Session session = getSession();
        Query query = session.createQuery("from StudentListSkills where studentId=:stId").setParameter("stId", stId);
        List<StudentListSkills> skills = query.list();
        session.close();
        HashMap<String, UserSkill> result = new HashMap<>();
        for (StudentListSkills skill : skills) {
            SkillListEntity skillForMap = getSkillById(skill.getSkillId());
            result.put(skillForMap.getSkilName(),
                    new UserSkill(skill.getSkillId(), skillForMap.getSkilName(),
                            skill.getRating(), getCatNameById(skillForMap.getCatId())));
        }
        return result;
    }

    public static List<UserSkill> getStudentListSkillsById(int stId) {
        Session session = getSession();
        Query query = session.createQuery("from StudentListSkills where studentId=:stId").setParameter("stId", stId);
        List<StudentListSkills> skills = query.list();
        session.close();
        List<UserSkill> result = new ArrayList<>();
        for (StudentListSkills skill : skills) {
            SkillListEntity skillForMap = getSkillById(skill.getSkillId());
            result.add(new UserSkill(skill.getSkillId(), skillForMap.getSkilName(),
                    skill.getRating(), getCatNameById(skillForMap.getCatId())));
        }
        return result;
    }

    public static HashMap<String, UserSkill> getAdminSkillsById(int adId) {
        Session session = getSession();
        Query query = session.createQuery("from ModeratorListSkills where modId=:adId").setParameter("adId", adId);
        List<ModeratorListSkills> skills = query.list();
        session.close();
        HashMap<String, UserSkill> result = new HashMap<>();
        for (ModeratorListSkills skill : skills) {
            SkillListEntity skillForMap = getSkillById(skill.getSkillId());
            result.put(skillForMap.getSkilName(), new UserSkill(skill.getSkillId(), skillForMap.getSkilName(),
                    skill.getRating(), getCatNameById(skillForMap.getCatId())));
        }
        return result;
    }

    public static List<UserSkill> getAdminSkillList(int adId) {
        Session session = getSession();
        Query query = session.createQuery("from ModeratorListSkills where modId=:adId").setParameter("adId", adId);
        List<ModeratorListSkills> skills = query.list();
        session.close();
        List<UserSkill> result = new ArrayList<>();
        for (ModeratorListSkills skill : skills) {
            SkillListEntity skillForMap = getSkillById(skill.getSkillId());
            result.add(new UserSkill(skill.getSkillId(), skillForMap.getSkilName(),
                    skill.getRating(), getCatNameById(skillForMap.getCatId())));
        }
        return result;
    }

    public static String getCatNameById(int catId) {
        Session session = getSession();
        List<SkillCatEntity> cats = session.createQuery("from SkillCatEntity where catId=:catId")
                .setParameter("catId", catId).list();
        session.close();
        return cats.get(0).getCatName();

    }

    public static List<SkillListEntity> getSkillByCat(int catId) {
        Session session = getSession();
        List<SkillListEntity> result = session.createQuery("From SkillListEntity where catId = :catId")
                .setParameter("catId", catId).list();
        session.close();
        return result;
    }

    public static void saveNewAdminSkillsSet(HashSet<Integer> skills, Integer adminId) {
        List<ModeratorListSkills> skillPair = new ArrayList<>();
        for (Integer i : skills) {
            ModeratorListSkills pair = new ModeratorListSkills();
            pair.setModId(adminId);
            pair.setRating(0);
            pair.setSkillId(i);
            pair.setPairId();
            skillPair.add(pair);
        }
        Session session = getSession();
        session.beginTransaction();
        for (ModeratorListSkills pair : skillPair) {
            session.saveOrUpdate(pair);
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteAdminSkills(HashSet<Integer> skills, Integer adminId) {
        List<ModeratorListSkills> skillPair = new ArrayList<>();
        for (Integer i : skills) {
            ModeratorListSkills pair = new ModeratorListSkills();
            pair.setModId(adminId);
            pair.setRating(0);
            pair.setSkillId(i);
            pair.setPairId();
            skillPair.add(pair);
        }
        Session session = getSession();
        session.beginTransaction();
        for (ModeratorListSkills pair : skillPair) {
            session.delete(pair);
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void saveNewStudentSkillsSet(HashSet<Integer> skills, Integer studentId) {
        List<StudentListSkills> skillPair = new ArrayList<>();
        for (Integer i : skills) {
            StudentListSkills pair = new StudentListSkills();
            pair.setStudentId(studentId);
            pair.setRating(0);
            pair.setSkillId(i);
            pair.setPairId();
            skillPair.add(pair);
        }
        Session session = getSession();
        session.beginTransaction();
        for (StudentListSkills pair : skillPair) {
            session.saveOrUpdate(pair);
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteNewStudentSkillsSet(HashSet<Integer> skills, Integer studentId) {
        List<StudentListSkills> skillPair = new ArrayList<>();
        for (Integer i : skills) {
            StudentListSkills pair = new StudentListSkills();
            pair.setStudentId(studentId);
            pair.setRating(0);
            pair.setSkillId(i);
            pair.setPairId();
            skillPair.add(pair);
        }
        Session session = getSession();
        session.beginTransaction();
        for (StudentListSkills pair : skillPair) {
            session.delete(pair);
        }
        session.getTransaction().commit();
        session.close();
    }

    public static List<ProjectStaffSkillEntity> getProjSkbyStaffId(int staffId) {
        Session session = getSession();
        Query query = session.createQuery("From ProjectStaffSkillEntity where slaveId = :slaveId");
        query.setParameter("slaveId", staffId);
        List<ProjectStaffSkillEntity> res = query.list();
        session.close();
        return res;
    }

    public static List<SkillCatEntity> getCategorySkill() {
        Session session = getSession();
        Query query = session.createQuery("From SkillCatEntity ");
        List<SkillCatEntity> res = query.list();
        session.close();
        return res;
    }

    public static Integer getCatIdByName(String catName) {
        Session session = getSession();
        List<SkillCatEntity> result = session.createQuery("From SkillCatEntity where catName = :catName")
                .setParameter("catName", catName).list();
        return result.get(0).getCatId();
    }
}