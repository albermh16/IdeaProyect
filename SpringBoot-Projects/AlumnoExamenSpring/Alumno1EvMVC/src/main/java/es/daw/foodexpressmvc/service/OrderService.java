package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.ErrorDTO;
import es.daw.foodexpressmvc.dto.OrderResponseDTO;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WebClient webClientAPI;

    public List<OrderResponseDTO> buscar(String status, Long userId, Long restaurantId){



        OrderResponseDTO[] orders = webClientAPI
                .get()
                .uri(uriBuilder -> {
                    var b = uriBuilder.path("/orders");
                    if(status != null && !status.isBlank()) b = b.queryParam("status", status);
                    if(userId != null) b = b.queryParam("userId", userId);
                    if(restaurantId != null) b = b.queryParam("restaurantId", restaurantId);
                    return b.build();
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(ErrorDTO.class)
                        .map(err -> new ConnectionApiRestException(err.getMessage())))
                .bodyToMono(OrderResponseDTO[].class)
                .block();

        return orders == null ? List.of() : Arrays.asList(orders);
    }

}
