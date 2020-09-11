package np.com.mithunadhikari.springdemo.service;

import lombok.AllArgsConstructor;
import np.com.mithunadhikari.springdemo.exceptions.SpringRedditException;
import np.com.mithunadhikari.springdemo.model.NotificationEmailModel;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmailModel notification) throws SpringRedditException {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setFrom("springreddit@email.com");
                helper.setTo(notification.getRecipient());
                helper.setSubject(notification.getSubject());
                helper.setText(mailContentBuilder.build(notification.getBody()));
            }

        };

        try {
            mailSender.send(preparator);

            log.info("Activation email sent!!");


        }catch (MailException exception){
            log.error("Exception occurred when sending mail", exception);

            throw  new SpringRedditException("Exception occured while sending email to "+notification.getRecipient(),exception);

        }

    }
}
