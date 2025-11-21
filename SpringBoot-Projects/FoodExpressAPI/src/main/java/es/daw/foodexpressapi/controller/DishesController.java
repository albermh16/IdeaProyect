package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.DishesDTO;
import es.daw.foodexpressapi.service.DishesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishesController {

    private final DishesService dishesService;

    @GetMapping
    public ResponseEntity <List<DishesDTO>> findAll() {
        return ResponseEntity.ok(dishesService.getAllDishes());
    }
}
