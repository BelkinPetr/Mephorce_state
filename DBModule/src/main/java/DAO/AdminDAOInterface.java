package DAO;

import HibernateEntities.ModeratorsEntity;
import HibernateEntities.ProjectsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kinetik on 24.04.17.
 */
public interface AdminDAOInterface {

    static HashMap<String, ModeratorsEntity> getAdminMap() {
        return null;
    };

    static ArrayList<String> getAdminList() {
        return null;
    };

    static void createNewAdmin(ModeratorsEntity admin) {};

    static void updateAdmin(ModeratorsEntity admin) {};

    static List<ProjectsEntity> getAdminProject(ModeratorsEntity admin) {
        return null;
    };

    static boolean checkIsAdmin(ModeratorsEntity admin) {
        return false;
    };
}
