package es.daw.hotelesapi.repository;

import es.daw.hotelesapi.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    Optional<Habitacion> findByCodigo(String codigo);

    List<Habitacion> findByHotel_CodigoAndOcupadaFalseAndTamanoGreaterThanEqualAndPrecioNocheBetween(
            String codigoHotel, int tamanoMinimo, BigDecimal precioMinimo, BigDecimal precioMaximo
    );

    List<Habitacion> findByHotel_CodigoAndOcupadaFalse(String codigoHotel);

}
