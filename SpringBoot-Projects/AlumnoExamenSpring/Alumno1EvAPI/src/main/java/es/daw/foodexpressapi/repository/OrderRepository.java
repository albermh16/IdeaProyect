package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);
    List<Order> findByUser_Id(Long userId);
    List<Order> findByRestaurant_Id(Long restaurantId);

    List<Order> findByStatusAndUser_Id(String status, Long userId);
    List<Order> findByStatusAndRestaurant_Id(String status, Long restaurantId);
    List<Order> findByRestaurant_IdAndUser_Id(Long restaurantId, Long userId);
    List<Order> findByStatusAndRestaurant_IdAndUser_Id(String status, Long restaurantId, Long userId);

}
