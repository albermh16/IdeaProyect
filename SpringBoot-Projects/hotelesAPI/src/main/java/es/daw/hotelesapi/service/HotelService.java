package es.daw.hotelesapi.service;

import es.daw.hotelesapi.dto.CategoriaDTO;
import es.daw.hotelesapi.dto.HotelRequestDTO;
import es.daw.hotelesapi.dto.HotelResponseDTO;
import es.daw.hotelesapi.entity.Categoria;
import es.daw.hotelesapi.entity.Hotel;
import es.daw.hotelesapi.exceptions.CategoriaNotFoundException;
import es.daw.hotelesapi.exceptions.HotelNotFoundException;
import es.daw.hotelesapi.repository.CategoriaRespository;
import es.daw.hotelesapi.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final CategoriaRespository categoriaRespository;


    public List<HotelResponseDTO> buscarHoteles(String localidad, String codigoCategoria) {
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
                .orElseThrow(() -> new CategoriaNotFoundException(dto.getCodigoCategoria()));

        Hotel hotel = toEntity(dto, categoria);

        Hotel hotelGuardado = hotelRepository.save(hotel);

        return toDTO(hotelGuardado);
    }

    public Hotel getByCodigo(String codigo) {
        return hotelRepository.findByCodigo(codigo)
                .orElseThrow(() -> new HotelNotFoundException(codigo));
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
