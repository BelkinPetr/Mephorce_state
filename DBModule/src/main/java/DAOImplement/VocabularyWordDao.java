package DAOImplement;

import HibernateEntities.translation.VocabularyWordEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import static HibernateUtil.HibernateUtil.getSession;

@Component
public class VocabularyWordDao extends GenericDao<VocabularyWordEntity> {

    public VocabularyWordDao() {
        super(VocabularyWordEntity.class);
    }

    public VocabularyWordEntity findByWord(String word) {
        Session session = getSession();
        return (VocabularyWordEntity) session.createQuery("from VocabularyWordEntity where word = :word")
                .setParameter("word", word)
                .list().stream().findFirst().orElse(null);
    }


}
