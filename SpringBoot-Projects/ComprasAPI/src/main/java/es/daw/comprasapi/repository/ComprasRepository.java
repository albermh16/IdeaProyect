package es.daw.comprasapi.repository;

import es.daw.comprasapi.entity.Compras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ComprasRepository extends JpaRepository<Compras, Long> {

    Optional<List<Compras>> findByFechaBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
}
