package np.com.mithunadhikari.springdemo.repository;

import np.com.mithunadhikari.springdemo.model.VerificationTokenModel;
import np.com.mithunadhikari.springdemo.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel,Long> {
}
