package DAOImplement;

import DAO.ClientsDAOInterface;
import HibernateEntities.ClientsEntity;
import HibernateEntities.ProjectsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by Кинетик on 31.12.2016.
 */
public class ClientsDao implements ClientsDAOInterface{
    public static HashMap<String, ClientsEntity> getClientMap(){
        Session session = getSession();
        Query query = session.createQuery("FROM ClientsEntity ");
        List<ClientsEntity> res = query.list();
        HashMap<String,ClientsEntity> clientsDict = new HashMap<>();
        for(ClientsEntity cl: res){
            clientsDict.put(cl.getClFamily()+" "+cl.getClFirstName()+" "+cl.getClSecName(),cl);
        }
        session.close();
        return clientsDict;
    }
    public static ArrayList<String> getClientList(){
        Session session = getSession();
        Query query = session.createQuery("FROM ClientsEntity ");
        List<ClientsEntity> res = query.list();
        ArrayList<String> clientsList = new ArrayList<>();
        for(ClientsEntity cl: res){
            clientsList.add(cl.getClFamily()+" "+cl.getClFirstName()+" "+cl.getClSecName());
        }
        session.close();
        return clientsList;
    }
    public static ClientsEntity createNewClient(ClientsEntity client) {
        Session session = getSession();
        Query query = session.createQuery("Select max(clientId) FROM ClientsEntity ");
        Integer res = (Integer)query.uniqueResult();
        client.setClientId(res+1);
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
        return client;
    }

    public static void updateClient(ClientsEntity client) {
        Session session = getSession();
        session.beginTransaction();
        session.update(client);
        session.getTransaction().commit();
        session.close();
    }

  /*  public static List<ProjectsEntity> getClientProject(ClientsEntity client) {
        Session session = getSession();
        Query query = session.createQuery("FROM ProjectsEntity pe JOIN ModeratorsEntity me on pe.mdId=me.modId Where pe.clId = :clientId");
        query.setParameter("clientId", client.getClientId());
        List<ProjectsEntity> res = query.list();
        session.close();
        return res;
    }*/
    public static List<ProjectsEntity> getClientProject(ClientsEntity client) {
        Session session = getSession();
        Query query = session.createQuery("FROM ProjectsEntity  Where clId = :clientId");
        query.setParameter("clientId", client.getClientId());
        List<ProjectsEntity> res = query.list();
        session.close();
        return res;
    }
    public static boolean checkIsClient(ClientsEntity client){
        Session session = getSession();
        Query query = session.createQuery("FROM ClientsEntity WHERE clFamily = :Family and clFirstName = :Name and clSecName = :SecName");
        query.setParameter("Family", client.getClFamily());
        query.setParameter("Name", client.getClFirstName());
        query.setParameter("SecName", client.getClSecName());
        List<ClientsEntity> res = query.list();
        if(res.size()==0){
            return false;
        }
        return true;
    }

    public static ClientsEntity getClientById(Integer clientId) {
        try(Session session = getSession()) {
            List<ClientsEntity> client = session.createQuery("from ClientsEntity where clientId=:clId")
                    .setParameter("clId", clientId).list();
            return client.get(0);
        } catch (Exception ex) {
            return null;
        }
    }
}
