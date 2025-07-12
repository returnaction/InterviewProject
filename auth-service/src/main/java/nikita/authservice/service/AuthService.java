package nikita.authservice.service;

import lombok.RequiredArgsConstructor;
import nikita.authservice.dto.LoginRequest;
import nikita.authservice.dto.RegisterRequest;
import nikita.authservice.dto.UserDto;
import nikita.authservice.feign.UserClient;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClient userClient;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        // kheshiruem parol' pered otpravkoy v user-service
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        // otpravlyaem zaregistrirovannogo pol'zovatelya v user-service cherez Feign
        userClient.register(request);

    }

    public String login(LoginRequest request){
        // zaprashivaem pol'zovatelya po username iz user-service
        UserDto user = userClient.findByUsername(request.getUsername());

        // sravnivaem vvedennyy parol' s zakheshirvannym iz user-service
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("\tНеверный логин или пароль");
        }
        // esli parol' podoshël — generiruem JWT-token s username i roley
        return jwtService.generateToken(user.getUsername(), user.getRole());
    }
}
