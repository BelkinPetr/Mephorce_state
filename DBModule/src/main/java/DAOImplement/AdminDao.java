package DAOImplement;

import DAO.AdminDAOInterface;
import HibernateEntities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by Кинетик on 31.12.2016.
 */
public class AdminDao implements AdminDAOInterface {
    public static HashMap<String, ModeratorsEntity> getAdminMap() {
        Session session = getSession();
        Query query = session.createQuery("FROM ModeratorsEntity ");
        List<ModeratorsEntity> res = query.list();
        HashMap<String, ModeratorsEntity> adminsDict = new HashMap<>();
        for (ModeratorsEntity ad : res) {
            adminsDict.put(ad.getModFamily() + " " + ad.getModFirstName() + " " + ad.getModSecName(), ad);
        }
        session.close();
        return adminsDict;
    }

    public static ArrayList<String> getAdminList() {
        Session session = getSession();
        Query query = session.createQuery("from ModeratorsEntity");
        List<ModeratorsEntity> res = query.list();
        ArrayList<String> adminsList = new ArrayList<>();
        for (ModeratorsEntity ad : res) {
            adminsList.add(ad.getModFamily() + " " + ad.getModFirstName() + " " + ad.getModSecName());
        }
        session.close();
        return adminsList;
    }

    public static ModeratorsEntity createNewAdmin(ModeratorsEntity admin) {
        Session session = getSession();
        Query query = session.createQuery("select max(modId) from ModeratorsEntity ");
        Integer res = (Integer) query.uniqueResult();
        admin.setModId(res + 1);
        session.beginTransaction();
        session.save(admin);
        session.getTransaction().commit();
        session.close();
        return admin;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void updateAdmin(ModeratorsEntity admin) {
        Session session = getSession();
        session.beginTransaction();
        session.update(admin);
        session.getTransaction().commit();
        session.close();
    }

    public static List<ProjectsEntity> getAdminProject(ModeratorsEntity admin) {
        Session session = getSession();
        Query query = session.createQuery("FROM ProjectsEntity Where clId = :modId");
        query.setParameter("modId", admin.getModId());
        List<ProjectsEntity> res = query.list();
        session.close();
        return res;
    }


    public static Deque<String> getClientForAdminProject(List<ProjectsEntity> projectsEntityList) {
        Deque<String> clientsDeque = new ArrayDeque<>();
        for (ProjectsEntity pr : projectsEntityList) {
            Session session = getSession();
            Criteria criteria = session.createCriteria(ClientsEntity.class);
            ClientsEntity cl = (ClientsEntity) criteria.add(Restrictions.eq("clientId", pr.getClId())).uniqueResult();
            clientsDeque.addLast(cl.peekFIO());
            session.close();
        }
        return clientsDeque;
    }

    public static boolean checkIsAdmin(ModeratorsEntity admin) {
        Session session = getSession();
        Query query = session.createQuery("FROM ModeratorsEntity WHERE modFamily = :Family and modFirstName = :Name and modSecName = :SecondName");
        query.setParameter("Family", admin.getModFamily());
        query.setParameter("Name", admin.getModFirstName());
        query.setParameter("SecondName", admin.getModSecName());
        List<ModeratorsEntity> res = query.list();
        if (res.size() == 0) {
            return false;
        }
        return true;
    }

    public static List<SkillCatEntity> getProjectSkills(Integer prId) {
        try (Session session = getSession()) {
            List<ProjectSkillCatEntity> themes = session.createQuery("from ProjectSkillCatEntity where prId=:projId")
                    .setParameter("projId", prId).list();
            List<SkillCatEntity> themeFaces = new ArrayList<>();
            for (ProjectSkillCatEntity projectSkillCatEntity : themes) {
                List<SkillCatEntity> tmp = session.createQuery("from SkillCatEntity where catId=:catId")
                        .setParameter("catId", projectSkillCatEntity.getSkilCatId()).list();
                themeFaces.add(tmp.get(0));
            }
            return themeFaces;
        } catch (Exception ex) {
            return null;
        }
    }

    public static ProjectsEntity getProjectById(Integer prId) {
        try (Session session = getSession()) {
            List<ProjectsEntity> projects = session.createQuery("from ProjectsEntity where prId=:prId")
                    .setParameter("prId", prId).list();
            return projects.get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void updateProject(ProjectsEntity project) {
        Session session = getSession();
        session.beginTransaction();
        session.update(project);
        session.getTransaction().commit();
        session.close();
    }



    public static List<ProjectsEntity> getListofProjects(Integer modId, Integer status) {
        List<ProjectsEntity> projects = new ArrayList<>();
        try (Session session = getSession()) {
            List<InvitationEntityClient> invites = session.createQuery("from InvitationEntityClient where result=:res and modId=:modid")
                    .setParameter("res", status).setParameter("modid", modId).list();
            for (InvitationEntityClient invite : invites) {
                List<ProjectsEntity> tmp = session.createQuery("from ProjectsEntity where prId=:prId")
                        .setParameter("prId", invite.getPrId()).list();
                projects.add(tmp.get(0));
            }
            return projects;
        } catch (Exception ex) {
            return null;
        }

    }

    public static void saveInvitation(Integer prId, Integer modId, Integer status) {
        try (Session session = getSession()) {
            InvitationEntityClient invite = new InvitationEntityClient();
            invite.setPrId(prId);
            invite.setModId(modId);
            invite.setResult(status);
            invite.setId();
            session.beginTransaction();
            session.saveOrUpdate(invite);
            session.getTransaction().commit();
        }
    }
}