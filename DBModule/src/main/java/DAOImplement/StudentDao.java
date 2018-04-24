package DAOImplement;

import DAO.StudentDAOInterface;
import HibernateEntities.StPrEntity;
import HibernateEntities.StudentsEntity;
import HibernateEntities.ProjectsEntity;
import HibernateEntities.TasksEntity;
import HibernateEntities.*;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

import static DAOImplement.AdminDao.distinctByKey;
import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by Кинетик on 01.01.2017.
 */
public class StudentDao implements StudentDAOInterface{
    public static List<StudentsEntity> loadStudent(Integer grId){
        Session session = getSession();
        Query query = session.createQuery("FROM StudentsEntity where stGroup="+grId);
        List<StudentsEntity> res = query.list();
        session.close();
        return res;
    }
    public static HashMap<Integer, StudentsEntity> getStudentsMap() {
        Session session = getSession();
        Query query = session.createQuery("from StudentsEntity");
        List<StudentsEntity> res = query.list();
        session.close();
        HashMap<Integer, StudentsEntity> studentsMap = new HashMap<>();
        for (StudentsEntity st : res) {
            studentsMap.put(st.getStudentsId(), st);
        }
        return studentsMap;
    }
    public static StudentsEntity createNewStudent(StudentsEntity student) {
        Session session = getSession();
        Query query = session.createQuery("Select max(studentsId) FROM StudentsEntity ");
        Integer res = (Integer)query.uniqueResult();
        student.setStudentsId(res+1);
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    public static void updateStudent(StudentsEntity student) {
        Session session = getSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
    }
    public static boolean checkIsStudent(StudentsEntity student){
        Session session = getSession();
        Query query = session.createQuery("FROM StudentsEntity Where stFamily = :Family and stFirstName = :Name and stSecName = :SecName and stGroup = :GroupId");
        query.setParameter("Family", student.getStFamily());
        query.setParameter("Name", student.getStFirstName());
        query.setParameter("SecName", student.getStSecName());
        query.setParameter("GroupId", student.getStGroup());
        List<StudentsEntity> res = query.list();
        if(res.size()==0){
            return false;
        }
        return true;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static List<ProjectsEntity> getStudentsProject(StudentsEntity student) {
        Session session = getSession();
        Query query = session.createQuery("From StPrEntity where stId = :studentsId");
        query.setParameter("studentsId", student.getStudentsId());
        List<StPrEntity> res = query.list();
        List<ProjectsEntity> resultOfQuery = new ArrayList<>();
        Query queryOne = session.createQuery("From ProjectsEntity where prId = :projId");
        for (StPrEntity stpr: res) {
            queryOne.setParameter("projId", stpr.getPrId());
            List<ProjectsEntity> resultTmp = queryOne.list();
            resultOfQuery.add(resultTmp.get(0));
        }
        session.close();
        List<ProjectsEntity> result = resultOfQuery.stream()
                .filter(distinctByKey(p -> p.getPrId())).collect(Collectors.toList());
        return result;
    }
    public static List<TasksEntity> getStudentsTasks(StudentsEntity student) {
        Session session = getSession();
        Query query = session.createQuery("From StPrEntity where stId = :studentsId");
        query.setParameter("studentsId", student.getStudentsId());
        List<StPrEntity> res = query.list();
        List<TasksEntity> resultOfQuery = new ArrayList<>();
        Query queryOne = session.createQuery("From TasksEntity where taskId = :taskId");
        for (StPrEntity stpr: res) {
            queryOne.setParameter("taskId", stpr.getTaskId());
            List<TasksEntity> resultTmp = queryOne.list();
            resultOfQuery.add(resultTmp.get(0));
        }
        session.close();
        return resultOfQuery;
    }

    public static List<SemantictasksEntity> getStudentsSemTasks(StudentsEntity student) {
        Session session = getSession();
        Query query = session.createQuery("From StPrEntity where stId = :studentsId");
        query.setParameter("studentsId", student.getStudentsId());
        List<StPrEntity> res = query.list();
        List<SemantictasksEntity> resultOfQuery = new ArrayList<>();
        Query queryOne = session.createQuery("From SemantictasksEntity where taskId = :taskId");
        for (StPrEntity stpr: res) {
            queryOne.setParameter("taskId", stpr.getSemtaskId());
            List<SemantictasksEntity> resultTmp = queryOne.list();
            resultOfQuery.add(resultTmp.get(0));
        }
        session.close();
        return resultOfQuery;
    }

    public void saveTask(TasksEntity tasksEntity) {
        Session session = getSession();
        session.beginTransaction();
        session.save(tasksEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void saveSemTask(SemantictasksEntity semantictasksEntity) {
        Session session = getSession();
        session.beginTransaction();
        session.save(semantictasksEntity);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateTask(TasksEntity tasksEntity) {
        Session session = getSession();
        session.beginTransaction();
        session.update(tasksEntity);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateSemTask(SemantictasksEntity semantictasksEntity) {
        Session session = getSession();
        session.beginTransaction();
        session.update(semantictasksEntity);
        session.getTransaction().commit();
        session.close();
    }

    public static List<TasksEntity> getStTaskByProject(Integer stId, Integer prId){
        Session session = getSession();
        List<TasksEntity> result = session.createQuery("from TasksEntity where studId= :stId and projId= :prId")
                .setParameter("stId", stId).setParameter("prId", prId).list();
        session.close();
        return  result;
    }

    public static List<TasksEntity> getStTasksByStudentId(Integer stId){
        Session session = getSession();
        List<TasksEntity> result = session.createQuery("from TasksEntity where studId= :stId")
                .setParameter("stId", stId).list();
        session.close();
        return  result;
    }

    public static List<SemantictasksEntity> getStSemTaskByProject(Integer stId, Integer prId){
        Session session = getSession();
        List<SemantictasksEntity> result = session.createQuery("from SemantictasksEntity where studId= :stId and projId= :prId")
                .setParameter("stId", stId).setParameter("prId", prId).list();
        //List<TasksEntity> result = session.createQuery("from TasksEntity ").list();
        session.close();
        return  result;
    }

    public static TasksEntity getTaskById(Integer taskId){
        Session session = getSession();
        List<TasksEntity> tasks = session.createQuery("from TasksEntity where taskId = :tsId")
                .setParameter("tsId", taskId).list();
        session.close();
        return tasks.get(0);
    }

    public static SemantictasksEntity getSemTaskById(Integer taskId){
        Session session = getSession();
        List<SemantictasksEntity> tasks = session.createQuery("from SemantictasksEntity where taskId = :tsId")
                .setParameter("tsId", taskId).list();
        session.close();
        return tasks.get(0);
    }


    public static List<TasksEntity> getTaskByProject(Integer prId){
        Session session = getSession();
        List<TasksEntity> result = session.createQuery("from TasksEntity where projId= :prId")
                .setParameter("prId", prId).list();
        //List<TasksEntity> result = session.createQuery("from TasksEntity ").list();
        session.close();
        return  result;
    }

    public static List<SemantictasksEntity> getSemTaskByProject (Integer prId){
        Session session = getSession();
        List<SemantictasksEntity> result = session.createQuery("from SemantictasksEntity where projId= :prId")
                .setParameter("prId", prId).list();
        //List<TasksEntity> result = session.createQuery("from TasksEntity ").list();
        session.close();
        return  result;
    }


    public static ProjectsEntity getProjectById(Integer prId){
        Session session = getSession();
        ProjectsEntity result = (ProjectsEntity) session.createQuery("from ProjectsEntity where prId =:prId")
                .setParameter("prId", prId).uniqueResult();
        session.close();
        return result;
    }


    public static List<StPrEntity> getSlaves(Integer prId){
        try(Session session = getSession()){
            List<StPrEntity> res = session.createQuery("from StPrEntity where prId =:prId")
                    .setParameter("prId", prId).list();
            return res;
        } catch (Exception ex) {
            return null;
        }
    }

    public static List<StudentsEntity> getSlavesFaces(Integer prId) {
        List<StPrEntity> stPrEntityList = getSlaves(prId);
        List<StudentsEntity> students = new ArrayList<>();
        try(Session session = getSession()) {
            for(StPrEntity stPrEntity: stPrEntityList) {
                List<StudentsEntity> tmp = session.createQuery("from StudentsEntity where studentsId=:stId")
                        .setParameter("stId", stPrEntity.getStId()).list();
                students.add(tmp.get(0));
            }
            List<StudentsEntity> result = students.stream()
                    .filter(distinctByKey(p -> p.getStudentsId())).collect(Collectors.toList());
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    public static List<StudentsEntity> getList(Integer prId, Integer status) {
        List<StudentsEntity> students = new ArrayList<>();
        try(Session session = getSession()) {
            List<InvitationEntity> invites = session.createQuery("from InvitationEntity where result=:res and prId=:prid")
                    .setParameter("res", status).setParameter("prid", prId).list();
            for(InvitationEntity invite: invites){
                List<StudentsEntity> tmp = session.createQuery("from StudentsEntity where studentsId=:stId")
                        .setParameter("stId", invite.getStId()).list();
                students.add(tmp.get(0));
            }
            return students;
        } catch (Exception ex) {
            return null;
        }

    }

    public static void saveInvite(Integer prId, Integer stId, Integer status){
        try(Session session = getSession()){
            InvitationEntity invite = new InvitationEntity();
            invite.setPrId(prId);
            invite.setStId(stId);
            invite.setResult(status);
            invite.setId();
            session.beginTransaction();
            session.saveOrUpdate(invite);
            session.getTransaction().commit();
        }
    }

    public static List<StudentsEntity> getSkilledStudents(Integer prId) {
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
            List<Integer> studentIds = new ArrayList<>();
            List<StudentListSkills> stSkills = session.createQuery("from StudentListSkills where skillId in :skillIds")
                    .setParameter("skillIds", skills).list();
            for(StudentListSkills tmp:stSkills){
                studentIds.add(tmp.getStudentId());
            }
            List<StudentsEntity> res = session.createQuery("from StudentsEntity where studentsId in :stIds")
                    .setParameter("stIds", studentIds).list();
            return res;
        } catch (Exception ex) {
            //TODO handle
            return null;
        }
    }

    public static StudentsEntity getStudentById(Integer stId){
        try(Session session = getSession()){
            return (StudentsEntity) session.createQuery("from StudentsEntity where studentsId=:stId")
                    .setParameter("stId", stId).uniqueResult();
        } catch(Exception ex) {
            return null;
        }
    }

    public static void addStudentToProject(Integer prId, Integer stId){
        try(Session session = getSession()){
            StPrEntity stPrEntity = new StPrEntity();
            stPrEntity.setPrId(prId);
            stPrEntity.setStId(stId);
            Query query = session.createQuery("Select max(id) FROM StPrEntity ");
            Integer res = (Integer)query.uniqueResult();
            res +=1;
            stPrEntity.setId(res);
            session.beginTransaction();
            session.save(stPrEntity);
            session.getTransaction().commit();
        } catch (Exception ex){
            //TODO handle exception
        }
    }
}