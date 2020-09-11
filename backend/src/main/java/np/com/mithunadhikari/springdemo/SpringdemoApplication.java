package np.com.mithunadhikari.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//to enable async operations like email sending
@EnableAsync
//alternate way to do async process is to use
//Message Queues like RabbitMQ or ActiveMQ, the also provide the reliability also
public class SpringdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdemoApplication.class, args);
	}

}

/*
*
* followed from here
* https://www.youtube.com/watch?v=DKlTBBuc32c&t=586s&ab_channel=freeCodeCamp.org
 */
