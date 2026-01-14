package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.OrderResponseDTO;
import es.daw.foodexpressapi.service.OrderService;
import es.daw.foodexpressapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public List<OrderResponseDTO> filterOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long restaurantId
    ) {

            return orderService.buscar(status, userId, restaurantId);

    }
}
