package DAO;

import HibernateEntities.ProjectsEntity;
import HibernateEntities.StudentsEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kinetik on 24.04.17.
 */
public interface StudentDAOInterface {

    static List<StudentsEntity> loadStudent(Integer grId){
        return null;
    }
    static HashMap<Integer, StudentsEntity> getStudentsMap() {
        return null;
    }
    static void createNewStudent(StudentsEntity student) {}

    static void updateStudent(StudentsEntity student) {}

    static boolean checkIsStudent(StudentsEntity student){
       return false;
    }
    static List<ProjectsEntity> getStudentsProject(StudentsEntity student) {
        return null;
    }
}
