package es.daw.hotelesapi.service;

import es.daw.hotelesapi.dto.HabitacionRequestDTO;
import es.daw.hotelesapi.dto.HabitacionResponseDTO;
import es.daw.hotelesapi.entity.Habitacion;
import es.daw.hotelesapi.entity.Hotel;
import es.daw.hotelesapi.repository.HabitacionRepository;
import es.daw.hotelesapi.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;
    private HotelRepository hotelRepository;

    private List<HabitacionResponseDTO> buscarHabitacion(String codigoHotel, Integer tamanoMin, BigDecimal precioMin, BigDecimal precioMax) {

        hotelRepository.findByCodigo(codigoHotel);

        if(tamanoMin != null && precioMin != null && precioMax != null) {
            return habitacionRepository.findByHotel_CodigoAndOcupadaFalseAndTamanoGreaterThanEqualAndPrecioNocheBetween
                    (codigoHotel, tamanoMin, precioMin, precioMax)
                    .stream()
                    .map(this::toDTO)
                    .toList();
        } else {
            return habitacionRepository.findByHotel_CodigoAndOcupadaFalse(codigoHotel)
                    .stream()
                    .map(this::toDTO)
                    .toList();
        }
    }

    public HabitacionResponseDTO crearHabitacion(HabitacionRequestDTO dto, String codigoHotel) {
        Hotel hotel = hotelRepository.findByCodigo(codigoHotel);
        Habitacion habitacion = toEntity(dto, hotel);

        Habitacion habitacionGuardada = habitacionRepository.save(habitacion);

        return toDTO(habitacionGuardada);
    }





    private Habitacion toEntity(HabitacionRequestDTO dto, Hotel hotel) {
        return Habitacion.builder()
                .codigo(dto.getCodigo())
                .tamano(dto.getTamano())
                .doble(dto.isDoble())
                .precioNoche(dto.getPrecioNoche())
                .incluyeDesayuno(dto.isIncluyeDesayuno())
                .hotel(hotel)
                .build();
    }

    private HabitacionResponseDTO toDTO(Habitacion entity) {
        return HabitacionResponseDTO.builder()
                .codigo(entity.getCodigo())
                .tamano(entity.getTamano())
                .doble(entity.isDoble())
                .precioNoche(entity.getPrecioNoche())
                .incluyeDesayuno(entity.isIncluyeDesayuno())
                .ocupada(entity.isOcupada())
                .build();
    }
}
