package np.com.mithunadhikari.springdemo.repository;

import np.com.mithunadhikari.springdemo.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostModel,Long> {

}
