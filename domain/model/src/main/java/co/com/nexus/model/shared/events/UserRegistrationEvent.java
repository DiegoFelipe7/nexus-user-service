package co.com.nexus.model.shared.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRegistrationEvent {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String birthDate;
}
