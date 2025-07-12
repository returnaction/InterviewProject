package nikita.authservice.feign;

import nikita.authservice.dto.RegisterRequest;
import nikita.authservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", path = "/api/users")
// Ukazyvaem, chto eto Feign-kliyent dlya obshcheniya s mikroservisom user-service
// Vse zaprosy budut nachinat'sya s /api/users
public interface UserClient {

    // TODO I don't have that endpoint yet
    @GetMapping("/by-username")
        // GET /api/users/by-username?username=...
        // Iщem pol'zovatelya po username, vozvrashaem UserDto
    UserDto findByUsername(@RequestParam String username);

    @PostMapping
        // POST /api/users/register s telom zaprosa RegisterRequest
        // Rehistriruem novogo pol'zovatelya cherez user-service
    void register(@RequestBody RegisterRequest request);
}
