package DAOImplement;

import HibernateEntities.TaskRating;
import org.hibernate.Session;

import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by ilysko on 29.06.17.
 */
public class TaskRatingDao {
    public static TaskRating getStudentTask(int taskId) {
//        Для задачи однозначно определяется испонитель, модератор,
//        категория (skill thematic)
//        taskId в таблице уникален
        Session session = getSession();
        List<TaskRating> result = session.createQuery("FROM TaskRating WHERE taskId = :taskId")
                .setParameter("taskId", taskId).list();
        session.close();
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public static int getStudentAcceptedTaskAmountForLast30Days(int studentId) {
        Session session = getSession();
        List<TaskRating> result = session.createQuery("FROM TaskRating WHERE studentId = :studentId")
                .setParameter("studentId", studentId).list();
        session.close();
        return  (int) result.stream()
                .filter(TaskRating::isAcceptByModerator)
                .count();
    }

    public static float getAverageTaskAcceptedAmountByStudents() {
        Session session = getSession();
        List<Integer> studentIdList = session.createQuery("SELECT DISTINCT studentId FROM TaskRating").list();
        int sum = 0;
        for (int studentId : studentIdList) {
            sum += getStudentAcceptedTaskAmountForLast30Days(studentId);
        }
        return sum / studentIdList.size();
    }
 }
