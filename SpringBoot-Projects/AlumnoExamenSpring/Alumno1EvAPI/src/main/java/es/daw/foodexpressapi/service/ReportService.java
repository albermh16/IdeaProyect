package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.report.CustomerSpendDTO;
import es.daw.foodexpressapi.dto.report.TopDishesDTO;
import es.daw.foodexpressapi.dto.report.TopOrderRestaurantDTO;
import es.daw.foodexpressapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final OrderRepository orderRepository;

    public List<CustomerSpendDTO> findCustomerSpend(){
        return orderRepository.findCustomerSpend();
    }

    public List<TopOrderRestaurantDTO> getTopRestaurantsByOrder(){
        return orderRepository.getTopRestaurantsByOrder();
    }

    public List<TopDishesDTO> getTopDishes(int limit){
        return orderRepository.getTopDishes().stream().limit(limit).toList();
    }

}
