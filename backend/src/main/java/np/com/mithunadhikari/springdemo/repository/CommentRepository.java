package np.com.mithunadhikari.springdemo.repository;

import np.com.mithunadhikari.springdemo.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel,Long> {
}
