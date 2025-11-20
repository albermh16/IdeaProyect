package es.daw.practicaproductosapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Inyectamos tus beans propios
    private final JwtFilter jwtAuthFilter;                 // filtro que validará el JWT en cada request
    private final UserDetailsService userDetailsService;   // tu CustomUserDetailsService

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // API con JWT: no usamos CSRF (no hay sesión ni cookies server-side)
                .csrf(csrf -> csrf.disable())

                // Si usas H2 console: desactiva frameOptions para que se vea en navegador
                .headers(h -> h.frameOptions(f -> f.disable()))

                // JWT = stateless: no hay sesión de servidor
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Qué rutas son públicas y cuáles requieren autenticación
                .authorizeHttpRequests(reg -> reg
                        .requestMatchers("/auth/**", "/h2-console/**").permitAll()  // login/registro y H2 abiertos
                        .anyRequest().authenticated()                                // el resto, autenticado
                )

                // Quién autentica (con qué encoder y cómo carga usuarios)
                .authenticationProvider(authenticationProvider())

                // Inserta TU filtro JWT antes del filtro de login por formulario
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // de dónde saca los usuarios (tu servicio que implementa UserDetailsService)
        provider.setUserDetailsService(userDetailsService);

        // cómo comprueba contraseñas (BCrypt, igual que en tu register)
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt para almacenar/verificar contraseñas
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            org.springframework.security.authentication.AuthenticationConfiguration config
    ) throws Exception {
        // AuthenticationManager que usarás en /auth/login
        return config.getAuthenticationManager();
    }
}
