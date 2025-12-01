package es.daw.hotelesmvc.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.hoteles.url}")
    private String apiHotelesUrl;

    @Value("${api.hoteles.auth-url}")
    private String apiHotelesAuthUrl;

    @Bean
    public WebClient hotelesWebClient(){
        return WebClient.builder()
                .baseUrl(apiHotelesUrl)
                .build();
    }

    @Bean
    public WebClient hotelesWebClientAuth(){
        return WebClient.builder()
                .baseUrl(apiHotelesAuthUrl)
                .build();
    }
}
