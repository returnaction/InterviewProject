package nikita.userservice.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.userservice.kafka.event.UserCreatedEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventProducer {

    private final StreamBridge streamBridge;

    public void sendUserCreatedEvent(UserCreatedEvent event){
        boolean send = streamBridge.send("userCreated-out-0", event);
        log.info("\t\uD83D\uDCE4 Отправка события в Кафка {}", event);
        if(!send){
            log.error("\t❌ Не удалось отправить сообщение в Кафка");
        }

    }
}
