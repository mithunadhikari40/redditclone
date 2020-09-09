package np.com.mithunadhikari.springdemo.controller;


import np.com.mithunadhikari.springdemo.dto.RegisterRequest;
import org.hibernate.result.UpdateCountOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public  void createAccount(@RequestBody RegisterRequest registerRequest){

    }

}
