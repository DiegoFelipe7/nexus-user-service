package co.com.nexus.rabbitmqconsumer.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.*;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    private final RabbitMqProperties properties;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(properties.getHost());
        connectionFactory.setPort(properties.getPort());
        connectionFactory.setUsername(properties.getUsername());
        connectionFactory.setPassword(properties.getPassword());
        connectionFactory.setVirtualHost(properties.getVirtualHost());
        connectionFactory.useNio();
        return connectionFactory;
    }

    @Bean
    public Mono<Connection> connectionMono(ConnectionFactory connectionFactory) {
        return Mono.fromCallable(() -> connectionFactory.newConnection("user-rabbitmq-consumer"))
                .cache();
    }

    @Bean
    public SenderOptions senderOptions(Mono<Connection> connectionMono) {
        return new SenderOptions()
                .connectionMono(connectionMono);
    }

    @Bean
    public Sender sender(SenderOptions senderOptions) {
        return RabbitFlux.createSender(senderOptions);
    }

    @Bean
    public ReceiverOptions receiverOptions(Mono<Connection> connectionMono) {
        return new ReceiverOptions()
                .connectionMono(connectionMono);
    }

    @Bean
    public Receiver receiver(ReceiverOptions receiverOptions) {
        return RabbitFlux.createReceiver(receiverOptions);
    }
}
