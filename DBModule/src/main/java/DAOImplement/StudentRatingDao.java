package DAOImplement;

import HibernateEntities.StudentsRating;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;

import static DAOImplement.SkillDao.getCatNameById;
import static HibernateUtil.HibernateUtil.getSession;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ilysko on 28.06.17.
 */
public class StudentRatingDao {
    public static void rateStudent(StudentsRating studentsRating,
                                   int informalRatingInt
                                   ) {
        Session session = getSession();
        session.beginTransaction();

        StudentsRating studentsRatingToSave = new StudentsRating();
        if (!isStudentExistByStudentIdAndCatId(studentsRating.getStudentId(),
                studentsRating.getSkillCatId())) {
            studentsRatingToSave.setStudentRatingId(getLastPrimaryKey() + 1);
            switch (informalRatingInt) {
                case 0:
                    studentsRatingToSave.setInformalMinusAmount(1);
                    studentsRatingToSave.setInformalPlusAmount(0);
                    studentsRatingToSave.setInformalZeroAmount(0);
                    break;
                case 1:
                    studentsRatingToSave.setInformalMinusAmount(0);
                    studentsRatingToSave.setInformalPlusAmount(0);
                    studentsRatingToSave.setInformalZeroAmount(1);
                    break;
                case 2:
                    studentsRatingToSave.setInformalMinusAmount(0);
                    studentsRatingToSave.setInformalPlusAmount(1);
                    studentsRatingToSave.setInformalZeroAmount(0);
                    break;
            }
        } else {
            studentsRatingToSave.setStudentRatingId(
                    getIdByStudentIdAndCatId(studentsRating.getStudentId(), studentsRating.getSkillCatId()));
            switch (informalRatingInt) {
                case 0:
                    studentsRatingToSave.setInformalMinusAmount(getCurrentInformalMinusAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ) + 1);
                    studentsRatingToSave.setInformalPlusAmount(getCurrentInformalPlusAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ));
                    studentsRatingToSave.setInformalZeroAmount(getCurrentInformalZeroAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ));
                    break;
                case 1:
                    studentsRatingToSave.setInformalMinusAmount(getCurrentInformalMinusAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ));
                    studentsRatingToSave.setInformalPlusAmount(getCurrentInformalPlusAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ));
                    studentsRatingToSave.setInformalZeroAmount(getCurrentInformalZeroAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ) + 1);
                    break;
                case 2:
                    studentsRatingToSave.setInformalMinusAmount(getCurrentInformalMinusAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ));
                    studentsRatingToSave.setInformalPlusAmount(getCurrentInformalPlusAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ) + 1);
                    studentsRatingToSave.setInformalZeroAmount(getCurrentInformalZeroAmount(
                            studentsRating.getStudentId(), studentsRating.getSkillCatId()
                    ));
                    break;
            }
        }

        studentsRatingToSave.setStudentId(studentsRating.getStudentId());
        studentsRatingToSave.setSkillCatId(studentsRating.getSkillCatId());
        studentsRatingToSave.setFormalRating(studentsRating.getFormalRating());

        session.saveOrUpdate(studentsRatingToSave);
        session.getTransaction().commit();
        session.close();
    }

    private static int getCurrentInformalZeroAmount(int studentId, int skillCatId) {
        Session session = getSession();
        List<Integer> res  = session.createQuery("SELECT informalZeroAmount " +
                "FROM StudentsRating WHERE studentId = :studentId and" +
                " skillCatId = :skillCatId")
                .setParameter("studentId", studentId)
                .setParameter("skillCatId", skillCatId).list();
        return res.get(0);
    }

    private static int getCurrentInformalPlusAmount(int studentId, int skillCatId) {
        Session session = getSession();
        List<Integer> res  = session.createQuery("SELECT informalPlusAmount " +
                "FROM StudentsRating WHERE studentId = :studentId and" +
                " skillCatId = :skillCatId")
                .setParameter("studentId", studentId)
                .setParameter("skillCatId", skillCatId).list();
        return res.get(0);
    }

    private static int getCurrentInformalMinusAmount(int studentId, int skillCatId) {
        Session session = getSession();
        List<Integer> res  = session.createQuery("SELECT informalMinusAmount " +
                "FROM StudentsRating WHERE studentId = :studentId and" +
                " skillCatId = :skillCatId")
                .setParameter("studentId", studentId)
                .setParameter("skillCatId", skillCatId).list();
        return res.get(0);
    }


    private static boolean isStudentExistByStudentIdAndCatId(int studentId, int skillCatId) {
        Session session = getSession();
        List<StudentsRating> res  = session.createQuery("FROM StudentsRating WHERE studentId = :studentId and" +
                " skillCatId = :skillCatId")
                .setParameter("studentId", studentId)
                .setParameter("skillCatId", skillCatId).list();
        return res.size() != 0;
    }

    private static int getIdByStudentIdAndCatId(int studentId, int skillCatId) {
        Session session = getSession();
        Query query = session.createQuery("FROM StudentsRating WHERE studentId = :studentId and" +
                " skillCatId = :skillCatId");
        query.setParameter("studentId", studentId);
        query.setParameter("skillCatId", skillCatId);
        List<StudentsRating> res = query.list();
        return res.get(0).getStudentRatingId();
    }

    private static int getLastPrimaryKey() {
        Session session = getSession();
        List<StudentsRating> result = session.createQuery("from StudentsRating ORDER BY students_rating_id").list();
        return result.get(result.size() - 1).getStudentRatingId();
    }

    public static HashMap<String, Pair<String, Float>> getStudentRatingById(int studentId) {
        Session session = getSession();
        List<StudentsRating> queryResult = session.createQuery("from StudentsRating where studentId=:studentId")
                .setParameter("studentId", studentId).list();
        HashMap<String, Pair<String, Float>> resMap = new HashMap<>();
        for (StudentsRating studentRating : queryResult) {
            StringBuilder informalRatingInString = new StringBuilder();
            informalRatingInString.append(studentRating.getInformalPlusAmount() + " \"+\" / " +
                    studentRating.getInformalZeroAmount() + " \"0\" / " +
                    studentRating.getInformalMinusAmount() + " \"-\"");
            Pair<String, Float> informalRatingPair = new Pair(informalRatingInString, studentRating.getFormalRating());
            resMap.put(getCatNameById(studentRating.getSkillCatId()), informalRatingPair);
        }
        return resMap;
    }
}