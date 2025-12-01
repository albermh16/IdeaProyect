package es.daw.hotelesapi.service;

import es.daw.hotelesapi.dto.HabitacionRequestDTO;
import es.daw.hotelesapi.dto.HabitacionResponseDTO;
import es.daw.hotelesapi.entity.Habitacion;
import es.daw.hotelesapi.entity.Hotel;
import es.daw.hotelesapi.exceptions.HabitacionNotFoundException;
import es.daw.hotelesapi.repository.HabitacionRepository;
import es.daw.hotelesapi.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;
    private final HotelService hotelService;

    public List<HabitacionResponseDTO> buscarHabitacion(String codigoHotel, Integer tamanoMin, BigDecimal precioMin, BigDecimal precioMax) {

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
        Hotel hotel = hotelService.getByCodigo(codigoHotel);


        Habitacion habitacion = toEntity(dto, hotel);

        Habitacion habitacionGuardada = habitacionRepository.save(habitacion);

        return toDTO(habitacionGuardada);
    }

    public HabitacionResponseDTO ocupar (String codigoHabitacion){
        Habitacion habitacion = habitacionRepository.findByCodigo(codigoHabitacion)
                .orElseThrow(() ->  new HabitacionNotFoundException(codigoHabitacion));

        habitacion.setOcupada(true);

        return toDTO(habitacionRepository.save(habitacion));
    }

    public void borrar(String codigoHabitacion){

        Habitacion habitacion  = habitacionRepository.findByCodigo(codigoHabitacion)
                .orElseThrow(() -> new HabitacionNotFoundException(codigoHabitacion));

        habitacionRepository.delete(habitacion);
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
