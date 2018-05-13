package DAOImplement;

import HibernateEntities.*;
import HibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by postgres on 24.03.2017.
 */
public class ProjectDao {
    public static HashMap<String, ProjectsEntity> getProjectMap(){
        Session session = getSession();
        Query query = session.createQuery("FROM ProjectsEntity ");
        List<ProjectsEntity> res = query.list();
        HashMap<String, ProjectsEntity> projectsDict = new HashMap<>();
        for(ProjectsEntity cl: res){
            projectsDict.put(cl.getWorktypes()+""+cl.getTitle()+" "+cl.getDescription()+" "+cl.getSum(),cl);
        }
        session.close();
        return projectsDict;
    }

    public static ProjectsEntity getProjectById(Integer prId){
        Session sesison = getSession();
        ProjectsEntity result = (ProjectsEntity) sesison.createQuery("From ProjectsEntity where prId = :prId")
                .setParameter("prId", prId).uniqueResult();
        sesison.close();
        return result;
    }

  /*  public static ArrayList<String> getClientList(){
        Session session = getSession();
        Query query = session.createQuery("FROM ClientsEntity ");
        List<ClientsEntity> res = query.list();
        ArrayList<String> clientsList = new ArrayList<>();
        for(ClientsEntity cl: res){
            clientsList.add(cl.getClFamily()+" "+cl.getClFirstName()+" "+cl.getClSecName());
        }
        session.close();
        return clientsList;
    }*/
    public static  void createNewProject(ProjectsEntity projectsEntity){
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("Select max(prId) FROM ProjectsEntity");
        Integer res = (Integer)query.uniqueResult();
        projectsEntity.setPrId(res.intValue() + 1);
        projectsEntity.setStatus("опубликован");
        session.beginTransaction();
        session.save(projectsEntity);
        session.getTransaction().commit();
        session.close();
    }
    public static void updateProject1(ProjectsEntity projectsEntity) {
        Session session = getSession();
        session.beginTransaction();
        session.update(projectsEntity);
        session.getTransaction().commit();
        session.close();
    }
    public static ModeratorsEntity getAdminById(Integer modId){
        Session session = getSession();
        ModeratorsEntity moderatorsEntity = (ModeratorsEntity) session.createQuery("from ModeratorsEntity where modId = :modId")
                .setParameter("modId", modId).uniqueResult();
        session.close();
        return moderatorsEntity;
    }
    /*public  static ModeratorsEntity getInfoAboutModeratorinProject(Integer modId,ProjectsEntity prId){
        Session session = getSession();
        ModeratorsEntity moderatorsEntity = (ModeratorsEntity) session.createQuery("from ModeratorsEntity where modId = :modId")
                .setParameter("modId", modId).uniqueResult();
        session.close();
        return moderatorsEntity;
    }*/

    public static ModeratorsEntity getAdminByName(String adminName){
        Session session = getSession();
        List<ModeratorsEntity> moderatorsEntity = session.createQuery("from ModeratorsEntity where modFamily = :Family")
                .setParameter("Family", adminName.split(" ")[0]).list();
        session.close();
        return moderatorsEntity.get(0);
    }
    public static List<ModeratorsEntity> getList(Integer prId, Integer status) {
        List<ModeratorsEntity> admin = new ArrayList<>();
        try(Session session = getSession()) {
            List<InvitationEntityClient> invites = session.createQuery("from InvitationEntityClient where result=:res and prId=:prId")
                    .setParameter("res", status).setParameter("prId", prId).list();
            for(InvitationEntityClient invite: invites){
                List<ModeratorsEntity> tmp = session.createQuery("from ModeratorsEntity  where modId=:modId")
                        .setParameter("modId", invite.getModId()).list();
                admin.add(tmp.get(0));
            }
            return admin;
        } catch (Exception ex) {
            return null;
        }

    }
    public static void addModeratorToProject(Integer prId, Integer modId) {
        try (Session session = getSession()) {
            MdPrEntity mdPrEntity = new MdPrEntity();
            mdPrEntity.setPrId(prId);
            mdPrEntity.setModId(modId);
            Query query = session.createQuery("Select max(id) FROM MdPrEntity ");
            Integer res = (Integer) query.uniqueResult();
            res += 1;
            mdPrEntity.setId(res);
            session.beginTransaction();
            session.save(mdPrEntity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            //TODO handle exception
        }
    }


    public static List<ModeratorsEntity> getSkilledModerators(Integer prId) {
        try(Session session = getSession()){
            List<Integer> skills = new ArrayList<>();
            List<ProjectSkillCatEntity> skillCats = session.createQuery("from ProjectSkillCatEntity where prId=:prId")
                    .setParameter("prId", prId).list();
            for(ProjectSkillCatEntity prSkill: skillCats){
                List<SkillListEntity> tmp = session.createQuery("from SkillListEntity where catId=:catId")
                        .setParameter("catId", prSkill.getSkilCatId()).list();
                for(SkillListEntity match: tmp){
                    skills.add(match.getSkilId());
                }
            }
            List<Integer> moderatorsIds = new ArrayList<>();
            List<ModeratorListSkills> stSkills = session.createQuery("from ModeratorListSkills where skillId in :skillIds")
                    .setParameter("skillIds", skills).list();
            for(ModeratorListSkills tmp:stSkills){
                moderatorsIds.add(tmp.getModId());
            }
            List<ModeratorsEntity> res = session.createQuery("from ModeratorsEntity where modId in :modIds")
                    .setParameter("modIds",moderatorsIds).list();
            return res;
        } catch (Exception ex) {
            //TODO handle
            return null;
        }
    }

    public static void saveInvite(Integer prId, Integer modId, Integer status){
        try(Session session = getSession()){
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
    public static List<ModeratorsEntity> getListofProjectsandModerators(Integer prId, Integer status) {
        List<ModeratorsEntity> moderators = new ArrayList<>();
        try (Session session = getSession()) {
            List<InvitationEntityClient> invites = session.createQuery("from InvitationEntityClient where result=:res and prId=:prid")
                    .setParameter("res", status).setParameter("prid", prId).list();
            for (InvitationEntityClient invite : invites) {
                List<ModeratorsEntity> tmp = session.createQuery("from ModeratorsEntity where modId=:modId")
                        .setParameter("modId", invite.getModId()).list();
                moderators.add(tmp.get(0));
            }
            return moderators;
        } catch (Exception ex) {
            return null;
        }

    }
    public static ProjectsEntity getProjectById1(Integer prId) {
        try (Session session = getSession()) {
            List<ProjectsEntity> projects = session.createQuery("from ProjectsEntity where prId=:prId")
                    .setParameter("prId", prId).list();
            return projects.get(0);
        } catch (Exception ex) {
            return null;
        }
    }

}
