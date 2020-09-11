package np.com.mithunadhikari.springdemo.exceptions;

import org.springframework.mail.MailException;

public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String message, MailException exception) {
        super(exception);
    } public SpringRedditException(String message) {
        super(message);
    }
}
