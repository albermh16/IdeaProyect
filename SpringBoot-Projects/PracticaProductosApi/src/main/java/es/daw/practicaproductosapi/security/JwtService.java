package es.daw.practicaproductosapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    //Se leen de .env gracias a spring dotenv

    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_EXPIRATION}")
    private String expiration;

    /**
     * Genera una clave nueva, aleatoria y segura en tiempo de ejecución.
     * Ideal para pruebas o ejemplos donde NO necesitas persistencia del token entre reinicios del servidor.
     * Cada vez que reinicias la app, se genera una nueva clave.
     * Esto hace que los tokens emitidos antes del reinicio ya no sean válidos, porque la firma ya no coincide.
     * @return
     */
    private SecretKey generateSecureKey() {
        return Jwts.SIG.HS256.key().build(); // Genera una clave segura aleatoria de 256 bits
    }

    /**
     * Devuelve la clave secreta usada para firmar y validar tokens.
     * @return
     */

    //Extraer username de un token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Generar un token simple para el usuario

    // Generar token con claims extra
    public String generateToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();

        Set<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        claims.put("roles", roles);

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 * Integer.parseInt(expiration)))
                .signWith(getSigningKey())
                .compact();

    }

    //Validar que el token pertenece al usuario y no esta expirado
    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    // Ha expirado?
    public boolean isTokenExpired(String token) {
        Date exp = extractClaim(token, Claims::getExpiration);
        return exp.before(new Date());
    }

    //Parsear y verificar firma -> obtener todos los claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Construir la key HMAC a partir del secreto Base64 del .env
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }




}
