package np.com.mithunadhikari.springdemo.controller;


import lombok.AllArgsConstructor;
import np.com.mithunadhikari.springdemo.dto.AuthenticationResponse;
import np.com.mithunadhikari.springdemo.dto.LoginRequest;
import np.com.mithunadhikari.springdemo.dto.RegisterRequest;
import np.com.mithunadhikari.springdemo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> createAccount(@RequestBody RegisterRequest registerRequest) {
        authService.createAccount(registerRequest);
        return new ResponseEntity<>("User Registration is successful", HttpStatus.OK);

    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>(
                "Account activated successfully ", HttpStatus.OK
        );
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);

    }


}
