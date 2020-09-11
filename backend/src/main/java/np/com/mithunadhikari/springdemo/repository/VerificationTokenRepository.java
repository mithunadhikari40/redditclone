package np.com.mithunadhikari.springdemo.repository;

import np.com.mithunadhikari.springdemo.model.UserModel;
import np.com.mithunadhikari.springdemo.model.VerificationTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenModel,Long> {
    Optional<VerificationTokenModel> findByToken(String token);
}
