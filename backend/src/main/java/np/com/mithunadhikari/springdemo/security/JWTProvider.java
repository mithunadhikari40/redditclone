package np.com.mithunadhikari.springdemo.security;

 import io.jsonwebtoken.Jwts;
 import np.com.mithunadhikari.springdemo.exceptions.SpringRedditException;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.userdetails.User;
 import org.springframework.stereotype.Service;

 import javax.annotation.PostConstruct;
 import java.io.InputStream;
 import java.security.*;

@Service
public class JWTProvider {
    private KeyStore keyStore;
    private final String secret="nepalisbigindeed";
    private final String alias = "springblog";

    @PostConstruct
    public void init(){
        try {
            keyStore = KeyStore.getInstance("jks");
            //this wil be the location of the jks file
            InputStream inputStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(inputStream,secret.toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
            throw  new SpringRedditException("Exception occurred while loading the keystore file");
        }


    }

    public String generateToken(Authentication authentication){
        User userModel = (User) authentication.getPrincipal();

        //we can set the subject as the combination of username and email and or password also


        return   Jwts.builder().setSubject(userModel.getUsername())
                .signWith(getPrivateKey())
                .compact();

    }

    private Key getPrivateKey() {
        try {
            return   keyStore.getKey(alias,secret.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SpringRedditException("Exception occurred while retrieving public key from the keystore");

        }
    }
}
