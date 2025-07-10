package nikita.userservice.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import nikita.userservice.kafka.event.UserCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class UserEventConsumer {

    @Bean
    public Consumer<Message<UserCreatedEvent>> userCreatedConsumer(){
        return message -> {
            UserCreatedEvent event = message.getPayload();
            log.info("\t \uD83D\uDCE5 Полученно событие UserCreatedEvent: id={}, name={}, role={}",
                    event.getId(), event.getName(), event.getRole());
        };
    }
}
