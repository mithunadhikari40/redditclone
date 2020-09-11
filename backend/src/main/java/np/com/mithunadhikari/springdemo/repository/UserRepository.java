package np.com.mithunadhikari.springdemo.repository;

import np.com.mithunadhikari.springdemo.model.SubRedditModel;
import np.com.mithunadhikari.springdemo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByUsername(String username);
}
