package es.daw.hotelesmvc.service;

import es.daw.hotelesmvc.dto.HabitacionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    private final WebClient hotelesWebClient;
    private final ApiAuthService apiAuthService;

    public List<HabitacionDTO> buscarHabitacion (String codigoHotel, Integer tamanoMin, BigDecimal precioMin, BigDecimal precioMax) {

        String url;

        if(tamanoMin != null && precioMin != null && precioMax != null) {
            url = "/habitaciones/" + codigoHotel
                    + "/buscar?tamanoMin=" + tamanoMin
                    + "&precioMin=" + precioMin
                    + "&precioMax=" + precioMax;
        }else {
            url = "/habitaciones/"+codigoHotel+"/buscar";
        }

        HabitacionDTO[] habitaciones = hotelesWebClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(HabitacionDTO[].class)
                .block();

        return habitaciones != null ? Arrays.asList(habitaciones) : null;
    }

    public void eliminarHabitacion (String codigoHabitacion){

        String token = apiAuthService.getToken();

        hotelesWebClient
                .delete()
                .uri("/habitaciones/{codigoHabitacion}", codigoHabitacion)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public HabitacionDTO crearHabitacion (HabitacionDTO habitacion, String codigoHotel){
        String token = apiAuthService.getToken();

        HabitacionDTO nuevaHabitacion = hotelesWebClient
                .post()
                .uri("/habitaciones/{codigoHabitacion}", codigoHotel )
                .headers(h -> h.setBearerAuth(token))
                .bodyValue(habitacion)
                .retrieve()
                .bodyToMono(HabitacionDTO.class)
                .block();

        return nuevaHabitacion;
    }


}
