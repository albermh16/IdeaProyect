package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.CreateOrderDTO;
import es.daw.foodexpressapi.dto.OrderResponseDTO;
import es.daw.foodexpressapi.dto.OrderSummaryDTO;
import es.daw.foodexpressapi.service.OrderService;
import es.daw.foodexpressapi.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> filterOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long restaurantId,
            Pageable pageable
            ) {

            return ResponseEntity.ok(orderService.filterOrders(status, userId, restaurantId, pageable));

    }

    @GetMapping("/summaries")
    public ResponseEntity<Page<OrderSummaryDTO>> getOrderSummaries(Pageable pageable){
        return ResponseEntity.ok(orderService.getOrderSumaries(pageable));
    }

    @PostMapping
    public ResponseEntity<OrderSummaryDTO> createOrder(@Valid @RequestBody CreateOrderDTO dto){
        OrderSummaryDTO created = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
