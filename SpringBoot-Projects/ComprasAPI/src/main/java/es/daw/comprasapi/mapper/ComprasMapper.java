package es.daw.comprasapi.mapper;

import es.daw.comprasapi.dto.ComprasDTO;
import es.daw.comprasapi.entity.Compras;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComprasMapper {

    @Mapping(target = "productosId", ignore = true)
    ComprasDTO toComprasDTO(Compras compras);

    @Mapping(target="id", ignore = true)
    @Mapping(target="listaProductos", ignore = true)
    Compras toComprasEntity(ComprasDTO comprasDTO);

    List<ComprasDTO> toComprasDTO(List<Compras> compras);
}
