package DAOImplement;

import HibernateEntities.translation.TranslationTextEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

@Component
public class TranslationTextDao extends GenericDao<TranslationTextEntity> {

    public TranslationTextDao() {
        super(TranslationTextEntity.class);
    }

    public List<TranslationTextEntity> getUnassignedTexts() {
        Session session = getSession();
        Query query = session.createQuery("from TranslationTextEntity t where student is null");
        List<TranslationTextEntity> unassignedTexts = query.list();
        session.close();
        return unassignedTexts;

    }


}
