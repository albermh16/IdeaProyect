package es.daw.comprasapi.service;

import es.daw.comprasapi.dto.ComprasDTO;
import es.daw.comprasapi.entity.Compras;
import es.daw.comprasapi.entity.Productos;
import es.daw.comprasapi.mapper.ComprasMapper;
import es.daw.comprasapi.repository.ComprasRepository;
import es.daw.comprasapi.repository.ProductoRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComprasService {

    private final ComprasRepository comprasRepository;
    private final ProductoRespository productoRespository;
    private final ComprasMapper comprasMapper;;

    public List<ComprasDTO> findAll() {
        List<Compras> compras = comprasRepository.findAll();
        return comprasMapper.toComprasDTO(compras);
    }

    public Optional <List<ComprasDTO>> findByDate (LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        Optional <List<Compras>> compras = comprasRepository.findByFechaBetween(start, end);

        if(compras.isEmpty()) return Optional.empty();

        return Optional.of(comprasMapper.toComprasDTO(compras.get()));
    }

    public Optional <ComprasDTO> createCompra (ComprasDTO dto){
        List <Productos> productos = dto.getProductosId()
                .stream()
                .map(id ->
                        productoRespository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Producto con id: " + id + " no encontrado")))
                                .toList();

        Compras nuevaCompra = comprasMapper.toComprasEntity(dto);
        nuevaCompra.setListaProductos(productos);
        comprasRepository.save(nuevaCompra);
        return Optional.of(comprasMapper.toComprasDTO(nuevaCompra));



    }
}
