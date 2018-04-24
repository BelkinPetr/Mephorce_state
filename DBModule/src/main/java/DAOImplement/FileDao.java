package DAOImplement;

import HibernateEntities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static HibernateUtil.HibernateUtil.getSession;

/**
 * Created by Irishka on 01.06.2017.
 */
public class FileDao {

    public static List<FilesEntity> getFileList (ProjectsEntity project) {
        Session session = getSession();
        Query query = session.createQuery("FROM FilesEntity Where prId = :prId");
        query.setParameter("prId", project.getPrId());
        List<FilesEntity> res = query.list();
        session.close();
        return res;
    }
    public static void createNewFile(FilesEntity file) {
        Session session = getSession();
        Query query = session.createQuery("Select max(idfiles) FROM FilesEntity ");
        Integer res = (Integer)query.uniqueResult();
        file.setIdfiles(res+1);
        session.beginTransaction();
        session.save(file);
        session.getTransaction().commit();
        session.close();
    }

    public static void createNewPhoto(PhotosEntity photo) {
        Session session = getSession();
        Query query = session.createQuery("Select max(id_photo) FROM PhotosEntity ");
        Integer res = (Integer)query.uniqueResult();
        photo.setId_photo(res+1);
        session.beginTransaction();
        session.save(photo);
        session.getTransaction().commit();
        session.close();
    }

    public static List<PhotosEntity> getPhotoList (ModeratorsEntity admin) {
        Session session = getSession();
        Query query = session.createQuery("FROM PhotosEntity Where modId = :modId");
        query.setParameter("modId", admin.getModId());
        List<PhotosEntity> res = query.list();
        session.close();
        return res;
    }
    public static List<PhotosEntity> getPhotoListforClient (ClientsEntity client) {
        Session session = getSession();
        Query query = session.createQuery("FROM PhotosEntity Where clId = :clId");
        query.setParameter("clId", client.getClientId());
        List<PhotosEntity> res = query.list();
        session.close();
        return res;
    }

 /**  public static void createNewImage(ModeratorsEntity photo) {
        Session session = getSession();
        Query query = session.createQuery("Select max() FROM ModeratorsEntity ");
        Integer res = (Integer)query.uniqueResult();
        photo.setIdimage(res+1);
        session.beginTransaction();
        session.save(photo);
        session.getTransaction().commit();
        session.close();
    }
**/


}
