package co.com.nexus.rabbitmqconsumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "adapters.rabbitmq")
public class RabbitMqProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String virtualHost;
    private Integer connectionTimeout;
    private String queue;
}
