package nikita.authservice.controller;

import lombok.RequiredArgsConstructor;
import nikita.authservice.dto.JwtResponse;
import nikita.authservice.dto.LoginRequest;
import nikita.authservice.dto.RegisterRequest;
import nikita.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor // Lombok: avtomaticheski sozdaet konstruktor dlya final-poly AuthService
public class AuthController {

    private final AuthService authService; // Servis dlya obrabotki logiki registratsii i vhoda

    @PostMapping("/register") // Endpoint POST /api/auth/register
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request){
        authService.register(request); // Peredaem dannye na registratsiyu v servis
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Vozvrashchaem 201 Created bez tela
    }

    @PostMapping("/login") // Endpoint POST /api/auth/login
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request){
        String token = authService.login(request); // Poluchaem JWT-token iz servisa
        return ResponseEntity.ok(new JwtResponse(token)); // Vozvrashchaem 200 OK s tokenom v otvete
    }
}
