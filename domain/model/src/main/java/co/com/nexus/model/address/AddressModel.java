package co.com.nexus.model.address;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AddressModel {
    private UUID id;
    private UUID userId;
    private String fullName;
    private String phone;
    private String country;
    private String state;
    private String city;
    private String street;
    private String apartment;
    private String postalCode;
    private boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
