package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.DishDTO;
import es.daw.foodexpressmvc.dto.ErrorDTO;
import es.daw.foodexpressmvc.dto.PageResponse;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DishService {

    private final WebClient webClientAPI;

    public PageResponse<DishDTO> getAllDishes(int page, int size, String sort, String dir) {


        try{
            return webClientAPI.get()
                    .uri(uriBuilder ->
                        uriBuilder
                                .path("/dishes")
                                .queryParam("page", page)
                                .queryParam("size", size)
                                .queryParam("sort", sort + "," + dir)

                                .build()

                    )
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response ->
                            response.bodyToMono(ErrorDTO.class)
                                    .map(err -> new ConnectionApiRestException(err.getMessage())))
                    .bodyToMono(new ParameterizedTypeReference<PageResponse<DishDTO>>() {
                    })
                    .block();


        }catch (Exception e){
            throw new ConnectionApiRestException(e.getMessage());
        }


    }
}