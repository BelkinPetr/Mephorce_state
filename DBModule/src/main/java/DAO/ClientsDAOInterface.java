package DAO;

import HibernateEntities.ClientsEntity;
import HibernateEntities.ProjectsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kinetik on 24.04.17.
 */
public interface ClientsDAOInterface {
    static HashMap<String, ClientsEntity> getClientMap(){
        return null;
    }

    static ArrayList<String> getClientList(){
        return null;
    }

    static void createNewClient(ClientsEntity client) {};

    static void updateClient(ClientsEntity client) {};

    static List<ProjectsEntity> getClientProject(ClientsEntity client) {
        return null;
    }

    static boolean checkIsClient(ClientsEntity client){
        return false;
    }
}
