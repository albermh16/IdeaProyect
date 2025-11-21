package es.daw.foodexpressmvc.controller;


import es.daw.foodexpressmvc.dto.DishesDTO;
import es.daw.foodexpressmvc.service.DishesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DishesController {
    
    private final DishesService dishesService;
    
    @GetMapping("/dishes")
    public String listDishes(Model model) {

        List<DishesDTO> dishes = dishesService.getAllDishes();
        
        model.addAttribute("dishes", dishes);
        
        return "dishes";
        
    }

}
