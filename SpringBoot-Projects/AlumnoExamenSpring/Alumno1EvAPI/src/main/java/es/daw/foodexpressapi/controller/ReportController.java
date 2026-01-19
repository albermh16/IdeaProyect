package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.report.CustomerSpendDTO;
import es.daw.foodexpressapi.dto.report.TopDishesDTO;
import es.daw.foodexpressapi.dto.report.TopOrderRestaurantDTO;
import es.daw.foodexpressapi.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/customers/spend")
    public ResponseEntity<List<CustomerSpendDTO>> findCustomerSpend(){
        return ResponseEntity.ok(reportService.findCustomerSpend());
    }

    @GetMapping("/restaurants/top-by-orders")
    public ResponseEntity<List<TopOrderRestaurantDTO>> getTopRestaurantsByOrder(){
        return ResponseEntity.ok(reportService.getTopRestaurantsByOrder());
    }

    @GetMapping("/dishes/top-by-units")
    public ResponseEntity<List<TopDishesDTO>> getTopDishes(@RequestParam(defaultValue = "10") int limit){
        return ResponseEntity.ok(reportService.getTopDishes(limit));
    }


}
