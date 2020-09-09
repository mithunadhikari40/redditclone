package np.com.mithunadhikari.springdemo.service;


import lombok.AllArgsConstructor;
import np.com.mithunadhikari.springdemo.dto.RegisterRequest;
import np.com.mithunadhikari.springdemo.model.UserModel;
import np.com.mithunadhikari.springdemo.model.VerificationTokenModel;
import np.com.mithunadhikari.springdemo.repository.UserRepository;
import np.com.mithunadhikari.springdemo.repository.VerificationTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
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



    @Transactional
    public void createAccount(RegisterRequest request) {
        UserModel user = new UserModel();
        user.setUserName(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);

    }

    private String generateVerificationToken(UserModel userModel){

      String uuid=  UUID.randomUUID().toString();
        VerificationTokenModel verificationTokenModel = new VerificationTokenModel();
        verificationTokenModel.setToken(uuid);
        verificationTokenModel.setUserModel(userModel);

        verificationTokenRepository.save(verificationTokenModel);
        return uuid;


    }

}
