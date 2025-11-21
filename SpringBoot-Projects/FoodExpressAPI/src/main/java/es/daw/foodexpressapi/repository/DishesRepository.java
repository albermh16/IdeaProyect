package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
}
