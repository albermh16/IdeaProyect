package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.DishDTO;
import es.daw.foodexpressmvc.dto.PageResponse;
import es.daw.foodexpressmvc.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/dishes")
    public String listDishes(Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             @RequestParam(defaultValue = "name") String sort,
                             @RequestParam(defaultValue = "asc") String dir) {

        PageResponse<DishDTO> pageResponse = dishService.getAllDishes(page, size, sort, dir);

        model.addAttribute("page", pageResponse);
        model.addAttribute("dishes", pageResponse.getContent());

        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);

        return "dishes/dishes";
    }
}
