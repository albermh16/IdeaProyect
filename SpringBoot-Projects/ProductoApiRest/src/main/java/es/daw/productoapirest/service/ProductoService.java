package es.daw.productoapirest.service;

import es.daw.productoapirest.dto.ProductoDTO;
import es.daw.productoapirest.entity.Producto;
import es.daw.productoapirest.mapper.ProductoMapper;
import es.daw.productoapirest.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public List<ProductoDTO> findAll() {
        List<Producto> productosEntities = productoRepository.findAll();
        return productoMapper.toDtos(productosEntities);
    }

    public Optional<ProductoDTO> findByCodigo(String codigo) {
        //El repositorio siempre devuelve un optional
        Optional<Producto> productoEntity = productoRepository.findByCodigo(codigo);

        if(productoEntity.isPresent()){
            ProductoDTO productoDTO = productoMapper.toDto(productoEntity.get());
            return Optional.of(productoDTO);
        }else{
            return Optional.empty();
        }
    }
}