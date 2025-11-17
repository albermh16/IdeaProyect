package es.daw.practicaproductosapi.service;

import es.daw.practicaproductosapi.dto.ProductoDTO;
import es.daw.practicaproductosapi.entity.Fabricante;
import es.daw.practicaproductosapi.entity.Producto;
import es.daw.practicaproductosapi.mapper.ProductoMapper;
import es.daw.practicaproductosapi.repository.FabricanteRepository;
import es.daw.practicaproductosapi.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final FabricanteRepository fabricanteRepository;
    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    public List<ProductoDTO> findAll(){
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toDTOs(productos);
    }

    public Optional<ProductoDTO> findByCodigo(String codigo) {
        Optional<Producto> productos = productoRepository.findByCodigo(codigo);
        if(productos.isPresent()){
            ProductoDTO dto = productoMapper.toDto(productos.get());
            return Optional.of(dto);
        }else{
            return Optional.empty();
        }
    }

    public Optional<ProductoDTO> create(ProductoDTO dto) {
        Producto producto = productoMapper.toEntity(dto);

        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(dto.getCodigoFabricante());

        if(fabricanteOptional.isPresent()){
            producto.setFabricante(fabricanteOptional.get());
        }else{
            return Optional.empty();
        }

        Producto productoGuardado = productoRepository.save(producto);
        return Optional.of(productoMapper.toDto(productoGuardado));
    }

    public Optional<ProductoDTO> update(String codigo, ProductoDTO dto) {

        Producto producto = productoRepository.findByCodigo(codigo)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        dto.setNombre(dto.getNombre());
        dto.setPrecio(dto.getPrecio());
        dto.setCodigo(dto.getCodigo());

        Optional<Fabricante> fabricanteOpt = fabricanteRepository.findById(dto.getCodigoFabricante());

        if(fabricanteOpt.isPresent()){
            producto.setFabricante(fabricanteOpt.get());
        }else{
            return Optional.empty();
        }

        Producto productoActualizado = productoRepository.save(producto);

        return Optional.of(productoMapper.toDto(productoActualizado));
    }

    public Optional<ProductoDTO> patch (String codigo, ProductoDTO dto) {
        Producto producto = productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if(dto.getNombre() != null) producto.setNombre(dto.getNombre());
        if(dto.getPrecio() != null) producto.setPrecio(dto.getPrecio());
        if(dto.getCodigo() != null && !dto.getCodigo().equals(producto.getCodigo())){
            if(productoRepository.findByCodigo(dto.getCodigo()).isPresent()){
                return Optional.empty();
            }
            producto.setCodigo(dto.getCodigo());
        }

        Optional<Fabricante> fabricanteOpt = fabricanteRepository.findById(dto.getCodigoFabricante());
        if(fabricanteOpt.isPresent()){
            producto.setFabricante(fabricanteOpt.get());
        }else{
            return Optional.empty();
        }

        Producto productoActualizado = productoRepository.save(producto);
        return Optional.of(productoMapper.toDto(productoActualizado));
    }

    public boolean deleteByCodigo(String codigo) {
        Producto producto = productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoRepository.delete(producto);
        return true;
    }










}
