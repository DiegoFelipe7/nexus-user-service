package co.com.nexus.model.shared.constants;

public final class KafkaEvents {
    public static final String USER_REGISTRATION_EVENT = "user-registration-event";

    private KafkaEvents() {
        throw new IllegalStateException("Utility class");
    }
}
