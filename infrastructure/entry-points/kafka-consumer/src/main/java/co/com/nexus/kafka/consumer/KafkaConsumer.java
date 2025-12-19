package co.com.nexus.kafka.consumer;

import co.com.nexus.kafka.consumer.mapper.KafkaMapper;
import co.com.nexus.model.shared.constants.KafkaEvents;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.usecase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@Log4j2
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ReactiveKafkaConsumerTemplate<String, String> kafkaConsumer;
    private final CreateUserUseCase createUserUseCase;

    @EventListener(ApplicationStartedEvent.class)
    public Flux<Void> listenMessages() {
        return kafkaConsumer
                .receiveAutoAck()
                .publishOn(Schedulers.newBoundedElastic(Schedulers.DEFAULT_BOUNDED_ELASTIC_SIZE, Schedulers.DEFAULT_BOUNDED_ELASTIC_QUEUESIZE, "kafka"))
                .flatMap(record -> {
                    String topic = record.topic();
                    if (record.value() != null && !record.value().isEmpty()) {
                        log.info("Received message on topic {}: {}", topic, record.value());
                    } else {
                        log.info("Received message on topic {} with null value", topic);
                    }
                    switch (topic) {
                        case KafkaEvents.USER_REGISTRATION_EVENT:
                            return createUserUseCase.apply(KafkaMapper.mapEventToTopic(record.value(), UserModel.class));
                        default:
                            log.warn("Received message on unknown topic: {}", topic);
                            return Mono.empty();
                    }
                })
                .doOnError(error -> log.error("Error processing kafka record", error))
                .retry()
                .repeat();
    }
}
