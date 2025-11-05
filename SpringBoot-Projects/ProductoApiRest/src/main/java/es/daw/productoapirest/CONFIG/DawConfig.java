package es.daw.productoapirest.CONFIG;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config.daw")
@Data
@NoArgsConstructor
public class DawConfig {
    private String code;
    private String message;
}
