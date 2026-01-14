package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.OrderResponseDTO;
import es.daw.foodexpressmvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String showOrdersPage(Model model){
        List<OrderResponseDTO> orders = orderService.buscar(null, null, null);

        model.addAttribute("filter", new OrderResponseDTO());
        model.addAttribute("orders", orders);

        return "orders/orders-list";
    }

    @GetMapping("/search")
    public String buscarOrders(@ModelAttribute("filter") OrderResponseDTO filter, Model model){
        List<OrderResponseDTO> orders = orderService.buscar(filter.getStatus(), filter.getUserId(), filter.getRestaurantId());

        model.addAttribute("filter", filter);
        model.addAttribute("orders", orders);

        return "orders/orders-list";
    }


}

