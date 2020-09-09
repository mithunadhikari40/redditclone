package np.com.mithunadhikari.springdemo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NotificationEmailModel {

    private String subject;
    private String recipient;
    private String body;
}
