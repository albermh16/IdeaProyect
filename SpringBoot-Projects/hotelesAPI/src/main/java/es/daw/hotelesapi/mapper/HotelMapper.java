package es.daw.hotelesapi.mapper;

import es.daw.hotelesapi.dto.HotelResponseDTO;
import es.daw.hotelesapi.entity.Hotel;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

//    @Mapping(source = "categoria.id", target="categoria_id")
//    HotelResponseDTO toDto(Hotel entity);
//
//    @Mapping(target = "categoria", ignore = true)
//    Hotel toEntity(HotelResponseDTO dto);
//
//    List<HotelResponseDTO> toHotelDTO(List<Hotel> hotels);
//    List<Hotel> toEntitys(List<HotelResponseDTO> dtos);
}
