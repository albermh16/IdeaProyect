package es.daw.hotelesapi.mapper;

import es.daw.hotelesapi.dto.HotelDTO;
import es.daw.hotelesapi.entity.Hotel;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(source = "categoria.id", target="categoria_id")
    HotelDTO toDto(Hotel entity);

    @Mapping(target = "categoria", ignore = true)
    Hotel toEntity(HotelDTO dto);

    List<HotelDTO> toHotelDTO(List<Hotel> hotels);
    List<Hotel> toEntitys(List<HotelDTO> dtos);
}
