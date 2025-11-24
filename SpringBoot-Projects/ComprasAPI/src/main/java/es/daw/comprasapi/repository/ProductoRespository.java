package es.daw.comprasapi.repository;

import es.daw.comprasapi.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRespository extends JpaRepository<Productos, Long> {

}
