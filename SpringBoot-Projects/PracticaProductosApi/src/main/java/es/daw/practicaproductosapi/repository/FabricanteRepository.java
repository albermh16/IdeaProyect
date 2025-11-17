package es.daw.practicaproductosapi.repository;

import es.daw.practicaproductosapi.entity.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
    // básico ya incluido: save, findAll, findById, deleteById...
}
