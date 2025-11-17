package es.daw.practicaproductosapi.repository;

import es.daw.practicaproductosapi.entity.Fabricante;
import es.daw.practicaproductosapi.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByCodigo(String codigo);

}
