package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/dishes")
    public String listDishes(Model model) {

        model.addAttribute("dishes", dishService.getAllDishes());

        return "dishes/dishes";
    }
}
