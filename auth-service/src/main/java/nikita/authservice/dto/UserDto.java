package nikita.authservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password; // захешированный
    private String role;     // строка
}
