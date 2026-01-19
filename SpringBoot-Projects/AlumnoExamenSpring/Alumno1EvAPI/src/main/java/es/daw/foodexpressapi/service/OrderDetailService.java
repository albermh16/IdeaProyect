package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.OrderDetailViewDTO;
import es.daw.foodexpressapi.repository.OrderDetailRepository;
import es.daw.foodexpressapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    public List<OrderDetailViewDTO> getDetailsByOrderId(Long orderId){
        if(!orderRepository.existsById(orderId)){
            throw new RuntimeException("Pedido no encontrado");
        }

        return orderDetailRepository.findDetailsViewOrderId(orderId);
    }
}
