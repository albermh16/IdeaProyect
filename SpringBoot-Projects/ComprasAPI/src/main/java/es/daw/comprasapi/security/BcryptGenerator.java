package es.daw.comprasapi.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BcryptGenerator {

    @Bean
    CommandLineRunner printBcrypts() {
        return args -> {
            var enc = new BCryptPasswordEncoder();
            System.out.println("HASH alberto (1234): " + enc.encode("1234"));
            System.out.println("HASH admin   (1234): " + enc.encode("1234"));
        };
    }
}
