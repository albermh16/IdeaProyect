package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.ErrorDTO;
import es.daw.foodexpressmvc.dto.OrderResponseDTO;
import es.daw.foodexpressmvc.dto.PageResponse;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
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

    public PageResponse<OrderResponseDTO> buscar(String status,
                                                 Long userId,
                                                 Long restaurantId,
                                                 int page,
                                                 int size,
                                                 String sort,
                                                 String dir){



        PageResponse<OrderResponseDTO> orders = webClientAPI
                .get()
                .uri(uriBuilder -> {
                    var b = uriBuilder.path("/orders")
                            .queryParam("page", page)
                            .queryParam("size", size)
                            .queryParam("sort", sort + "," + dir);
                    if(status != null && !status.isBlank()) b = b.queryParam("status", status);
                    if(userId != null) b = b.queryParam("userId", userId);
                    if(restaurantId != null) b = b.queryParam("restaurantId", restaurantId);
                    return b.build();
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(ErrorDTO.class)
                        .map(err -> new ConnectionApiRestException(err.getMessage())))
                .bodyToMono(new ParameterizedTypeReference<PageResponse<OrderResponseDTO>>() {
                })
                .block();

        return orders == null ? new PageResponse<>() : orders;
    }

}
