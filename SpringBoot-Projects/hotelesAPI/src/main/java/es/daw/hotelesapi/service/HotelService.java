package es.daw.hotelesapi.service;

import es.daw.hotelesapi.dto.CategoriaDTO;
import es.daw.hotelesapi.dto.HotelRequestDTO;
import es.daw.hotelesapi.dto.HotelResponseDTO;
import es.daw.hotelesapi.entity.Categoria;
import es.daw.hotelesapi.entity.Hotel;
import es.daw.hotelesapi.repository.CategoriaRespository;
import es.daw.hotelesapi.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private HotelRepository hotelRepository;
    private CategoriaRespository categoriaRespository;


    public List<HotelResponseDTO> buscarHoteles(String codigoCategoria, String localidad) {
        List<Hotel> hoteles;

        if(localidad != null){
            hoteles = hotelRepository.findByLocalidadIsIgnoreCase(localidad);
        } else if(codigoCategoria != null){
            hoteles = hotelRepository.findByCategoria_Codigo(codigoCategoria);

        } else {
            hoteles = hotelRepository.findAll();
        }

        return hoteles.stream()
                .map(this::toDTO)
                .toList();

    }

    public HotelResponseDTO crearHotel(HotelRequestDTO dto) {
        Categoria categoria = categoriaRespository.findByCodigo(dto.getCodigoCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        Hotel hotel = toEntity(dto, categoria);

        Hotel hotelGuardado = hotelRepository.save(hotel);

        return toDTO(hotelGuardado);
    }




    private Hotel toEntity(HotelRequestDTO dto, Categoria categoria) {
        return Hotel.builder()
                .codigo(dto.getCodigo())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .piscina(dto.isPiscina())
                .localidad(dto.getLocalidad())
                .categoria(categoria)
                .build();
    }

    private HotelResponseDTO toDTO(Hotel entity) {
        return HotelResponseDTO.builder()
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .piscina(entity.isPiscina())
                .localidad(entity.getLocalidad())
                .categoria(CategoriaDTO.builder()
                        .codigo(entity.getCategoria().getCodigo())
                        .nombre(entity.getCategoria().getNombre())
                        .build())
                .build();
    }
}
