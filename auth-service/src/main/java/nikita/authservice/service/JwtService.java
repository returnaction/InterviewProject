package nikita.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // TODO - vynesti v .yml pozzhe
    private final String SECRET = "my-jwt-secret-my-jwt-secret-my-jwt-secret";
    // Sekretnyy klyuch dlya podpisaniya JWT (minimum 32 simvola dlya algoritma HS256)

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Preobrazuyem stroku v sekretnyy klyuch posle sozdaniya bina (posle konstruktora)
        secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, String role) {
        // Sozdaem JWT-token s username, roley, datoy sozdaniya i srokom deystviya (1 den')
        return Jwts.builder().setSubject(username) // Osnovnaya informatsiya (subject) — username
                .claim("role", role) // Dobavlyaem kastomnoe pole "role"
                .setIssuedAt(new Date()) // Data vydachi tokena
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token deystvitelen 1 den'
                .signWith(secretKey, SignatureAlgorithm.HS256) // Podpisyvaem token sekretnym klyuchom
                .compact(); // Sobiraem token v stroku
    }
}
