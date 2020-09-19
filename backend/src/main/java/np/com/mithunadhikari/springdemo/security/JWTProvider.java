package np.com.mithunadhikari.springdemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import np.com.mithunadhikari.springdemo.exceptions.SpringRedditException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JWTProvider {
    private final String secret = "mithunadhikari";
    private final String alias = "redditclone";
    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("jks");
            //this wil be the location of the jks file
            InputStream inputStream = getClass().getResourceAsStream("/redditclone.jks");
            keyStore.load(inputStream, secret.toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
            throw new SpringRedditException("Exception occurred while loading the keystore file");
        }


    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        //we can set the subject as the combination of username and email and or password also

//        SignatureAlgorithm algorithm = SignatureAlgorithm.ES256;


        return Jwts.builder().setSubject(user.getUsername())
                .signWith(getPrivateKey()/*,algorithm*/)
                .compact();

    }

    private PrivateKey getPrivateKey() {
        try {
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, secret.toCharArray());
            boolean isNull = privateKey == null;
            System.out.println("The private key created is null" + isNull);
            System.out.println("The private key created is null" + privateKey.getAlgorithm());
            return privateKey;
        } catch (Exception e) {
            System.out.println("The exception not to get the key is " + e.getMessage());
            e.printStackTrace();
            throw new SpringRedditException("Exception occurred while retrieving private key from the keystore");

        }
    }

    public boolean validateToken(String token) {
        parser().setSigningKey(getPublicKey()).parseClaimsJws(token);
        return  true;
    }

    public String getUsernameFromToken(String token){
        Claims claims = parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate(alias).getPublicKey();

        } catch (Exception e) {
            System.out.println("The exception not to get the key is " + e.getMessage());
            e.printStackTrace();
            throw new SpringRedditException("Exception occurred while retrieving public key from the keystore");

        }
    }
}
