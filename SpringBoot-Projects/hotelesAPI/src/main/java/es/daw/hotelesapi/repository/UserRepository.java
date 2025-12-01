package es.daw.hotelesapi.repository;

import es.daw.hotelesapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Buscar por username de usuario (campo "name" de la entidad)
    Optional <User> findByUsername(String username);
}
