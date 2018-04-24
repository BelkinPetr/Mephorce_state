package converters;

import HibernateEntities.NewsEntity;
import dto.NewsDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author nivanov
 *         on 22.06.17.
 */

@Component
public class NewsConverter implements Converter<NewsDTO, NewsEntity>{

    @Override
    public NewsEntity convert(NewsDTO newsDTO) {
        NewsEntity entity = new NewsEntity();
        entity.setCreator(newsDTO.getCreator());
        entity.setDate(new Date());
        entity.setProject(newsDTO.getProjectName());
        entity.setText(newsDTO.getText());
        return entity;
    }

}
