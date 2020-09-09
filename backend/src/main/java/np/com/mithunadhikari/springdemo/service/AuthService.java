package np.com.mithunadhikari.springdemo.service;


import np.com.mithunadhikari.springdemo.dto.RegisterRequest;
import np.com.mithunadhikari.springdemo.model.UserModel;
import np.com.mithunadhikari.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    public void createAccount(RegisterRequest request){
        UserModel user = new UserModel();
        user.setUserName(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

    }

}
