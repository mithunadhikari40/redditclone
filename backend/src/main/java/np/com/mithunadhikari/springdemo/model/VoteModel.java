package np.com.mithunadhikari.springdemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class VoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    private VoteType voteType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId",referencedColumnName = "postId")
    private PostModel postModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private  UserModel userModel;
}
