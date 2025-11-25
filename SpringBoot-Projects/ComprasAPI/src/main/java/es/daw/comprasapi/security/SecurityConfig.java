package es.daw.comprasapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())                // API REST, sin formularios
                .headers(h -> h.frameOptions(f -> f.disable())) // permitir /h2-console
                .authorizeHttpRequests(auth -> auth
                        // H2 console pública
                        .requestMatchers("/h2-console/**").permitAll()

                        // REGLAS DEL ENUNCIADO
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()          // público
                        .requestMatchers(HttpMethod.POST, "/api/**").authenticated()     // cualquier autenticado
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")     // solo ADMIN
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")  // solo ADMIN

                        // cualquier otra petición, autenticada
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {}) // misma forma que usabas: autenticación Basic
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
