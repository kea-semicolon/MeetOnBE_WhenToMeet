package semicolon.MeetOn_WhenToMeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class MeetOnWhenToMeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetOnWhenToMeetApplication.class, args);
	}

}
