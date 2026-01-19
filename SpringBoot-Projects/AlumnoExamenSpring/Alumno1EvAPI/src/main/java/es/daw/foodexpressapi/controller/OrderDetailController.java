package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.OrderDetailViewDTO;
import es.daw.foodexpressapi.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping("/order/{orderId}/details")
    public ResponseEntity<List<OrderDetailViewDTO>> getDetailsByOrderID(@PathVariable Long orderId){
        return ResponseEntity.ok(orderDetailService.getDetailsByOrderId(orderId));
    }
}
