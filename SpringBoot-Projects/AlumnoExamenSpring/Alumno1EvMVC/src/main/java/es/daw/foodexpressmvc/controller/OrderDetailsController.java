package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.OrderDetailViewDTO;
import es.daw.foodexpressmvc.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order-details")
public class OrderDetailsController {

    private final OrderDetailService orderDetailService;

    @GetMapping("/order/{orderId}/details")
    public String orderDetailsByOrderId(@PathVariable Long orderId, Model model){

        List<OrderDetailViewDTO> orderDetails = orderDetailService.findDetailsByOrderId(orderId);

        model.addAttribute("orderId", orderId);
        model.addAttribute("details", orderDetails);

        return "orders/order-details";


    }
}
