package es.daw.hotelesapi.repository;

import es.daw.hotelesapi.entity.Categoria;
import es.daw.hotelesapi.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitacionRespository extends JpaRepository<Habitacion, Long> {
    Optional<Habitacion> findByCodigo(String codigo);

    Optional<Habitacion> findByCodigoHotel(Long Long);

}
