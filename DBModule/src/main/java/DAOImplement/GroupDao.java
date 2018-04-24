package DAOImplement;

import HibernateEntities.GroupsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by Кинетик on 01.01.2017.
 */
public class GroupDao {
    public static HashMap<String,Integer> getGroupMap() {
        Session session = getSession();
        Query query = session.createQuery("FROM GroupsEntity ");
        List<GroupsEntity> res = query.list();
        HashMap<String,Integer> groupMap = new HashMap<>();
        for(GroupsEntity gr:res){
            groupMap.put(gr.getGroupName(),gr.getGroupId());
        }
        session.close();
        return groupMap;
    }
    public static List<GroupsEntity> getGroupList() {
        Session session = getSession();
        Query query = session.createQuery("FROM GroupsEntity ");
        List<GroupsEntity> res = query.list();
        session.close();
        return res;
    }
}
