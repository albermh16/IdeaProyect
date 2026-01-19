package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.OrderDetailViewDTO;
import es.daw.foodexpressapi.entity.OrderDetailId;
import es.daw.foodexpressapi.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, OrderDetailId> {

    @Query("""
        SELECT new es.daw.foodexpressapi.dto.OrderDetailViewDTO(
                    d.name,
                    CAST(d.category AS string ),
                    od.quantity,
                    d.price,
                    od.subtotal
                )
                FROM OrderDetails od
                JOIN od.dish d
                WHERE od.order.id = :orderId
                ORDER BY d.name
""")
    List<OrderDetailViewDTO> findDetailsViewOrderId(@Param("orderId") Long orderId);
}
