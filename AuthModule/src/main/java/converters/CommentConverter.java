package converters;

import DAOImplement.CommunityNewsDao;
import HibernateEntities.CommentEntity;
import HibernateEntities.NewsEntity;
import dto.CommentDTO;
import exceptions.PageNotFoundException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * @author nivanov
 *         on 22.06.17.
 */

@Component
public class CommentConverter implements Converter<CommentDTO, CommentEntity> {

    @Override
    public CommentEntity convert(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setAuthor(dto.getAuthor());
        entity.setCreationTime(new Date());
        Optional<NewsEntity> newsEntityOptional = CommunityNewsDao.getNewsById(dto.getNewsID());
        entity.setNewsEntity(newsEntityOptional.orElseThrow(PageNotFoundException::new));
        entity.setText(dto.getText());
        return entity;
    }

}
