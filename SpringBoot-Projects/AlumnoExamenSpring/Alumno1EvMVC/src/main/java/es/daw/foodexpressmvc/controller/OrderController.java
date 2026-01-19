package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.OrderFilterDTO;
import es.daw.foodexpressmvc.dto.OrderResponseDTO;
import es.daw.foodexpressmvc.dto.PageResponse;
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

//    @GetMapping
//    public String showOrdersPage(Model model,
//                                 @RequestParam(defaultValue = "0") int page,
//                                 @RequestParam(defaultValue = "5") int size,
//                                 @RequestParam(defaultValue = "name") String sort,
//                                 @RequestParam(defaultValue = "asc") String dir
//                                 ){
//        PageResponse<OrderResponseDTO> orders = orderService.buscar(null, null, null,
//                                                                    page, size, sort, dir);
//
//        model.addAttribute("size", size);
//        model.addAttribute("sort", sort);
//        model.addAttribute("dir", dir);
//
//        model.addAttribute("page", orders);
//        model.addAttribute("filter", new OrderFilterDTO());
//        model.addAttribute("orders", orders.getContent());
//
//        return "orders/orders-list";
//    }

    @GetMapping
    public String buscarOrders(@ModelAttribute("filter") OrderFilterDTO filter,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "status") String sort,
                               @RequestParam(defaultValue = "asc") String dir,
                               Model model){
        PageResponse<OrderResponseDTO> orders = orderService.buscar(filter.getStatus(),
                                                                    filter.getUserId(),
                                                                    filter.getRestaurantId(),
                                                                    page, size, sort, dir);

        model.addAttribute("page", orders);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);

        model.addAttribute("filter", filter);
        model.addAttribute("orders", orders.getContent());

        return "orders/orders-list";
    }


}

