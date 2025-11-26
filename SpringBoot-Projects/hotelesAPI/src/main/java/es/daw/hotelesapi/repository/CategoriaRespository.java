package es.daw.hotelesapi.repository;

import es.daw.hotelesapi.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRespository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByCodigo(String codigo);
}
