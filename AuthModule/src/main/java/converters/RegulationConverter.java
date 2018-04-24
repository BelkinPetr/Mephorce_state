package converters;

import HibernateEntities.RegulationEntity;
import dto.RegulationDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author nivanov
 *         on 22.06.17.
 */

@Component
public class RegulationConverter implements Converter<RegulationDTO, RegulationEntity> {

    @Override
    public RegulationEntity convert(RegulationDTO regulationDTO) {
        RegulationEntity entity = new RegulationEntity();
        entity.setAuthor(regulationDTO.getAuthor());
        entity.setProject(regulationDTO.getProjectName());
        entity.setText(regulationDTO.getTitle());
        return entity;
    }
}
