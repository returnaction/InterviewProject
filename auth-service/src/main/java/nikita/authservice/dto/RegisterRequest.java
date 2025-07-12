package nikita.authservice.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    //TODO - Добавить валидации позже  @NotBlank @Size(min = 6)   @NotNull @Valid @RequestBody RegisterRequest request
    private String username;
    private String password;
    private String role;
}
