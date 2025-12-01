package es.daw.hotelesmvc.service;

import es.daw.hotelesmvc.dto.AuthRequestDTO;
import es.daw.hotelesmvc.dto.AuthResponseDTO;
import es.daw.hotelesmvc.exception.ConnectApiRestException;
import es.daw.hotelesmvc.session.ApiSessionToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ApiAuthService {

    //Con esto estoy llamando al /auth/login
    //@Qualifier("hotelesWebClientAuth")
    private final WebClient hotelesWebClientAuth;


    //AQUI GUARDO EL TOKEN QUE ME DEVUELVE LA API

    private final ApiSessionToken apiSessionToken;

    @Value("${api.hoteles.auth-username}")
    private String apiUsername;

    @Value("${api.hoteles.auth-password}")
    private String apiPassword;

    public String getToken(){
        // FORMA 1 (FoodExpress comentado):
        // String storedToken = (String) session.getAttribute("token");
        // if (storedToken != null) {
        //     return storedToken;
        // }

        if(apiSessionToken.getApiToken() != null){
            return apiSessionToken.getApiToken();
        }

        // SI ES NULO PEDIMOS UNO NUEVO A LA API Y PIDO EL TOKEN

        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername(apiUsername);
        request.setPassword(apiPassword);

        try{
            AuthResponseDTO response = hotelesWebClientAuth
                    .post()
                    .uri("/login")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(AuthResponseDTO.class)
                    .block();

            if(response == null || response.getToken() == null){
                throw new ConnectApiRestException("API login ha fallado: no se ha recibido token: ");
            }

            apiSessionToken.setApiToken(response.getToken());

            return apiSessionToken.getApiToken();

        } catch(Exception e){
            throw new ConnectApiRestException("API login ha fallado: " + e.getMessage());
        }
    }


}
