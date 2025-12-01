package es.daw.hotelesmvc.service;

import es.daw.hotelesmvc.dto.HotelDTO;
import es.daw.hotelesmvc.exception.ConnectApiRestException;
import es.daw.hotelesmvc.session.ApiSessionToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final WebClient hotelesWebClient;
    private final ApiAuthService apiAuthService;



    public List<HotelDTO> listarHoteles(){

        String token = apiAuthService.getToken();
        HotelDTO[] hotel;

       try{
           hotel = hotelesWebClient
                   .get()
                   .uri("/hoteles/buscar")
                   .headers(h -> h.setBearerAuth(token))
                   .retrieve()
                   .bodyToMono(HotelDTO[].class)
                   .block();
       }catch(Exception e){
           throw new ConnectApiRestException(e.getMessage());
       }

       return Arrays.asList(hotel);

    }
}
