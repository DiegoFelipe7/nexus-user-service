package co.com.nexus.rabbitmqconsumer.consumer;

import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.rabbitmqconsumer.config.RabbitMqProperties;
import co.com.nexus.usecase.user.CreateUserUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.ConsumeOptions;
import reactor.rabbitmq.Receiver;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegistrationConsumer {

    private final Receiver receiver;
    private final RabbitMqProperties rabbitMQProperties;
    private final CreateUserUseCase createUserUseCase;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void startConsuming() {
        consumeUserRegistrationEvents().subscribe();
    }

    public Flux<Void> consumeUserRegistrationEvents() {
        return receiver
                .consumeAutoAck(
                        rabbitMQProperties.getQueue(),
                        new ConsumeOptions().qos(10)
                )
                .flatMap(this::processMessage)
                .doOnError(error -> log.error("Error consuming user registration event", error))
                .retry()
                .onErrorResume(error -> {
                    log.error("Fatal error in consumer, stopping", error);
                    return Mono.empty();
                });
    }

    private Mono<Void> processMessage(Delivery delivery) {
        return Mono.fromCallable(() -> {
                    String message = new String(delivery.getBody());
                    return objectMapper.readValue(message, UserModel.class);
                })
                .flatMap(user -> createUserUseCase.apply(user)
                        .doOnSuccess(unused -> log.info("Successfully processed user registration event{}", user.getUserId()))
                        .doOnError(error -> log.error("Error processing user registration event", error))
                )
                .onErrorResume(error -> {
                    log.error("Failed to process message, skipping to next: {}", new String(delivery.getBody()), error);
                    return Mono.empty();
                });
    }

}
