package nikita.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Pomeshaem klass v Spring context kak konfiguratsionnyy klass
public class SecutiryConfig {

    @Bean // Sozdaem bin shifrovshchika paroley
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // Ispol'zuem algoritm BCrypt dlya kheshirivaniya paroley
    }

    // TODO Добавить цепочки после того как узнаем все наши пути
    @Bean // Sozdaem tsepochku filtrov bezopasnosti
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Razreshaem vse zaprosy bez autentifikatsii
                .csrf(csrf -> csrf.disable()); // Otklyuchaem zashchitu ot CSRF (dlya prostoty na etape razrabotki)
        return http.build(); // Vozvrashchaem nastroennuyu tsepochku
    }
}
