package DAOImplement;

import HibernateEntities.StudentRateLogEntity;
import org.hibernate.Session;

import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by ilysko on 01.07.17.
 */
public class SkillRateLogDao {

    public static void addStudentRateNote(StudentRateLogEntity studentRateLogEntity) {
        Session session = getSession();
        session.beginTransaction();
        if (isAnybodyHere()) {
            studentRateLogEntity.setId(getLastPrimaryKey() + 1);
        }
        session.saveOrUpdate(studentRateLogEntity);
        session.getTransaction().commit();
        session.close();
    }

    public static boolean isStudentWasntRatedOnProjectByModerator(
            int studentId, int projectId, int moderatorId
    ) {
        Session session = getSession();
        List res = session.createQuery("FROM StudentRateLogEntity WHERE " +
                "moderatorId = :moderatorId and " +
                "studentId = :studentId and " +
                "projectId = :projectId")
                .setParameter("moderatorId", moderatorId)
                .setParameter("studentId", studentId)
                .setParameter("projectId", projectId).list();
        return res.size() == 0;
    }

    private static int getLastPrimaryKey() {
        Session session = getSession();
        List<StudentRateLogEntity> result = session.createQuery("from StudentRateLogEntity ORDER BY students_rate_log_id").list();
        return result.get(result.size() - 1).getId();
    }

    public static boolean isAnybodyHere() {
        Session session = getSession();
        List<StudentRateLogEntity> result = session.createQuery("from StudentRateLogEntity").list();
        return result.size() != 0;
    }
}
