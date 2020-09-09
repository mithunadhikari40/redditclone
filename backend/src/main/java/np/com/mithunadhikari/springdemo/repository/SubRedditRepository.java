package np.com.mithunadhikari.springdemo.repository;

import np.com.mithunadhikari.springdemo.model.SubRedditModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRedditRepository  extends JpaRepository<SubRedditModel,Long> {
}
