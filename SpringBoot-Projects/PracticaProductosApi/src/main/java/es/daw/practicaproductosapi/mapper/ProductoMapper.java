package es.daw.practicaproductosapi.mapper;

import es.daw.practicaproductosapi.dto.ProductoDTO;
import es.daw.practicaproductosapi.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    //entity to DTO
    // Toma el fabricante.codigo(Long) de fabricante y lo pone en dto.codigoFabricante
    @Mapping(source = "fabricante.codigo", target = "codigoFabricante")
    ProductoDTO toDto(Producto entity);

    // DTO to entity
    // Ignoramos "fabricante" por que se resuelve en el servicio a partir de dto.fabricante
    @Mapping(target = "fabricante", ignore = true)
    Producto toEntity(ProductoDTO dto);

    List<ProductoDTO> toDTOs(List<Producto> entities);
    List<Producto> toEntities(List<ProductoDTO> dtos);
}
