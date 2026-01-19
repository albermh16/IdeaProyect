package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.OrderSummaryDTO;
import es.daw.foodexpressapi.dto.report.CustomerSpendDTO;
import es.daw.foodexpressapi.dto.report.TopDishesDTO;
import es.daw.foodexpressapi.dto.report.TopOrderRestaurantDTO;
import es.daw.foodexpressapi.entity.Order;
import es.daw.foodexpressapi.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /*
    List<Order> findByStatus(String status);
    List<Order> findByUser_Id(Long userId);
    List<Order> findByRestaurant_Id(Long restaurantId);

    List<Order> findByStatusAndUser_Id(String status, Long userId);
    List<Order> findByStatusAndRestaurant_Id(String status, Long restaurantId);
    List<Order> findByRestaurant_IdAndUser_Id(Long restaurantId, Long userId);
    List<Order> findByStatusAndRestaurant_IdAndUser_Id(String status, Long restaurantId, Long userId);
*/


    @Query("""
             SELECT o
                FROM Order o
                WHERE (:status IS NULL OR o.status = :status)
                  AND (:userId IS NULL OR o.user.id = :userId)
                  AND (:restaurantId IS NULL OR o.restaurant.id = :restaurantId)
""")
    Page<Order> findByFilters(OrderStatus status, Long userId, Long restaurantId, Pageable pageable);


    @Query("""
    SELECT new es.daw.foodexpressapi.dto.report.CustomerSpendDTO(
            u.id,
            u.username,
            SUM(od.subtotal)
        )
        FROM Order o
        JOIN o.user u
        JOIN o.orderDetails od
        GROUP BY u.id, u.username
        ORDER BY SUM(od.subtotal) DESC
""")
    List<CustomerSpendDTO> findCustomerSpend();


    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.TopOrderRestaurantDTO(
            r.id,
            r.name,
            COUNT(o)
        )
        FROM Order o
        JOIN o.restaurant r
        GROUP BY r.id, r.name
        ORDER BY COUNT(o.id) DESC
""")
    List<TopOrderRestaurantDTO> getTopRestaurantsByOrder();


    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.TopDishesDTO(
            d.id,
            d.name,
            SUM(od.quantity)
        )
        FROM Order o
        JOIN o.orderDetails od
        JOIN od.dish d
        GROUP BY d.id, d.name
        ORDER BY SUM(od.quantity) DESC
""")
    List<TopDishesDTO> getTopDishes();

    @Query("""
    SELECT new es.daw.foodexpressapi.dto.OrderSummaryDTO(
        o.id,
        u.username,
        r.name,
        o.status,
        o.orderDate,
        SUM(od.quantity),
        SUM(od.subtotal)
    )
    FROM Order o
    JOIN o.orderDetails od
    JOIN o.user u
    JOIN o.restaurant r
    GROUP BY o.id, u.username, r.name, o.status, o.orderDate
    ORDER BY o.id
    
""")
    Page<OrderSummaryDTO> getAllOrderSummaries(Pageable pageable);

}
