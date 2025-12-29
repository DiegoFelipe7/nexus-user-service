package co.com.nexus.api.address.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAddressRequest {
    private String fullName;
    private String phone;
    private String country;
    private String state;
    private String city;
    private String street;
    private String apartment;
    private String postalCode;
    private boolean isDefault;
}
