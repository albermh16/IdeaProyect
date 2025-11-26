package es.daw.hotelesapi.mapper;

import es.daw.hotelesapi.dto.HabitacionDTO;
import es.daw.hotelesapi.entity.Habitacion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabitacionMapper {

    HabitacionDTO toDTO(Habitacion entity);

    Habitacion toEntity(HabitacionDTO dto);

    List<HabitacionDTO> toDTOs(List<Habitacion> entities);
    List<Habitacion> toEntitys(List<HabitacionDTO> dtos);
}
