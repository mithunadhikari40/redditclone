package np.com.mithunadhikari.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class SubRedditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Community name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PostModel> posts;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel userModel;
}
