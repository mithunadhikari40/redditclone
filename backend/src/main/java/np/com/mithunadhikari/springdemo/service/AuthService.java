package np.com.mithunadhikari.springdemo.service;


import lombok.AllArgsConstructor;
import np.com.mithunadhikari.springdemo.dto.AuthenticationResponse;
import np.com.mithunadhikari.springdemo.dto.LoginRequest;
import np.com.mithunadhikari.springdemo.dto.RegisterRequest;
import np.com.mithunadhikari.springdemo.exceptions.SpringRedditException;
import np.com.mithunadhikari.springdemo.model.NotificationEmailModel;
import np.com.mithunadhikari.springdemo.model.UserModel;
import np.com.mithunadhikari.springdemo.model.VerificationTokenModel;
import np.com.mithunadhikari.springdemo.repository.UserRepository;
import np.com.mithunadhikari.springdemo.repository.VerificationTokenRepository;
import np.com.mithunadhikari.springdemo.security.JWTProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    //    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;
    final UserRepository userRepository;
    final VerificationTokenRepository verificationTokenRepository;
    final PasswordEncoder encoder;
    final AuthenticationManager authenticationManager;
    final private MailService mailService;
    final private JWTProvider jwtProvider;


    @Transactional
    public void createAccount(RegisterRequest request) {
        UserModel user = new UserModel();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmailModel(
                "Please activate your account",
                user.getEmail(),
                "Thank you for signing up for Spring Reddit" +
                        "Please click on the below url to activate" +
                        "your acount:" +
                        "http://localhost:8080/api/auth/accountVerification/" + token
        ));


    }

    private String generateVerificationToken(UserModel userModel) {

        String uuid = UUID.randomUUID().toString();
        VerificationTokenModel verificationTokenModel = new VerificationTokenModel();
        verificationTokenModel.setToken(uuid);
        verificationTokenModel.setUserModel(userModel);

        verificationTokenRepository.save(verificationTokenModel);
        return uuid;


    }

    public void verifyAccount(String token) {

        Optional<VerificationTokenModel> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid token"));

        fetchUserAndEnable(verificationToken.get());
    }


    @Transactional
    private void fetchUserAndEnable(VerificationTokenModel verificationTokenModel) {
        String username = verificationTokenModel.getUserModel().getUsername();
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringRedditException("Could not find user associated with that token" + username));

        userModel.setEnabled(true);
        userRepository.save(userModel);


    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token,loginRequest.getUsername());


    }
}
