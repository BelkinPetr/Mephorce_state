package DAOImplement;

import HibernateEntities.UsersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by Кинетик on 28.12.2016.
 */
public class UsersDao {
    public static HashMap<String, UsersEntity> getUserObjectList(){
        Session session = getSession();
        Query query = session.createQuery("FROM UsersEntity");
        List<UsersEntity> res = query.list();
        HashMap<String, UsersEntity> userDict = new HashMap<>();
        for(UsersEntity us: res){
            userDict.put(us.getUserLogin(),us);
        }
        session.close();
        return userDict;
    }
    public static HashMap<String, String> getUserNameList(){
        Session session = getSession();
        Query query = session.createQuery("FROM UsersEntity");
        List<UsersEntity> res = query.list();
        HashMap<String, String> userDict = new HashMap<>();
        for(UsersEntity us: res){
            userDict.put(us.getUserLogin(), us.getUserLogin());
        }
        session.close();
        return userDict;
    }
}
