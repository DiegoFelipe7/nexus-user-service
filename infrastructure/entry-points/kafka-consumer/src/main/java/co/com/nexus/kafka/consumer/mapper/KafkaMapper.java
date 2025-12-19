package co.com.nexus.kafka.consumer.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaMapper {
    private KafkaMapper() {
        throw new IllegalStateException("Utility class");
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T mapEventToTopic(String event, Class<T> clazz) {
        try {
            return mapper.readValue(event, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializando el evento", e);
        }
    }

}
