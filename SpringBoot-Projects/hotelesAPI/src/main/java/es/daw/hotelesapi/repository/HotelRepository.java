package es.daw.hotelesapi.repository;

import es.daw.hotelesapi.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByCodigo(String codigo);
    List<Hotel> findByCategoria_Codigo(String codigoCategoria);
    List<Hotel> findByLocalidadIsIgnoreCase(String localidad);
}
