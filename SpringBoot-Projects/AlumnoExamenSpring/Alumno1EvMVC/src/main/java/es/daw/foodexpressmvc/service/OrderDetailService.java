package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.ErrorDTO;
import es.daw.foodexpressmvc.dto.OrderDetailViewDTO;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final WebClient webClientAPI;

    public List<OrderDetailViewDTO> findDetailsByOrderId(Long orderId){

        List<OrderDetailViewDTO> ordersDetails = webClientAPI
                .get()
                .uri("/order-details/order/{orderId}/details", orderId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(ErrorDTO.class)
                        .map(err -> new ConnectionApiRestException(err.getMessage())))
                .bodyToMono(new ParameterizedTypeReference<List<OrderDetailViewDTO>>() {})
                .block();

        return ordersDetails == null ? List.of() : ordersDetails;


    }

}
