package DAOImplement;

import HibernateEntities.NewsEntity;
import HibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * @author Nastya Golubeva
 */
public class CommunityNewsDao {
//Метод загрузки списка новостей
    public static List<NewsEntity> getNewsList() {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM NewsEntity ");
        List<NewsEntity> res = query.list();
        session.getTransaction().commit();
        session.close();
        return res;
    }

    /**
     * Метод получения новости по id
     * @param id id новости в бд
     * @return {@link Optional} объекта новости
     */
    public static Optional<NewsEntity> getNewsById(Integer id) {
        Session session = getSession();
        session.beginTransaction();
        Query<NewsEntity> newsEntityQuery =
                session.createQuery("FROM NewsEntity AS ne WHERE ne.newsId = :id", NewsEntity.class);
        newsEntityQuery.setParameter("id", id);
        session.getTransaction().commit();
        return newsEntityQuery.uniqueResultOptional();
    }

//Метод сохраняющий новую новость в базу данных
    public static void addNews(NewsEntity news) {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("select max(newsId) from NewsEntity ");
        Integer res = (Integer) query.uniqueResult();
        if (res == null){
            res = 0;
        }
        news.setNewsId(res + 1);
        session.save(news);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateNews(NewsEntity news) {
        Session session = getSession();
        session.beginTransaction();
        session.update(news);
        session.getTransaction().commit();
        session.close();
    }
}
