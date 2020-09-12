package np.com.mithunadhikari.springdemo.security;

 import com.sun.el.parser.JJTELParserState;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import np.com.mithunadhikari.springdemo.exceptions.SpringRedditException;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.userdetails.User;
 import org.springframework.stereotype.Service;

 import javax.annotation.PostConstruct;
 import java.io.InputStream;
 import java.security.Key;
 import java.security.KeyStore;
 import java.security.PrivateKey;
 import java.security.PublicKey;
 import java.util.Collection;

@Service
public class JWTProvider {
    private KeyStore keyStore;
    private final String secret="mithunadhikari";
    private final String alias = "redditclone";

    @PostConstruct
    public void init(){
        try {
            keyStore = KeyStore.getInstance("jks");
            //this wil be the location of the jks file
            InputStream inputStream = getClass().getResourceAsStream("/redditclone.jks");
            keyStore.load(inputStream,secret.toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
            throw  new SpringRedditException("Exception occurred while loading the keystore file");
        }


    }

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        //we can set the subject as the combination of username and email and or password also

//        SignatureAlgorithm algorithm = SignatureAlgorithm.ES256;


        return   Jwts.builder().setSubject(user.getUsername())
                .signWith(getPrivateKey()/*,algorithm*/)
                .compact();

    }

    private PrivateKey getPrivateKey() {
        try {
            PrivateKey privateKey= (PrivateKey)   keyStore.getKey(alias,secret.toCharArray());
            boolean isNull = privateKey == null;
            System.out.println("The private key created is null"+isNull);
            return  privateKey;
        } catch (Exception e) {
            System.out.println("The exception not to get the key is "+e.getMessage());
            e.printStackTrace();
            throw new SpringRedditException("Exception occurred while retrieving public key from the keystore");

        }
    }
}
