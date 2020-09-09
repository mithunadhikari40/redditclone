package np.com.mithunadhikari.springdemo.repository;

import np.com.mithunadhikari.springdemo.model.SubRedditModel;
import np.com.mithunadhikari.springdemo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
}
