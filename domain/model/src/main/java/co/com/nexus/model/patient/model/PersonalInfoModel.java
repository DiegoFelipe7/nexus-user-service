package co.com.nexus.model.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class PersonalInfoModel {
    private String firstName;
    private String lastName;
    private String email;
    private String documentType;
    private String documentNumber;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String city;
    private String country;
    private String postalCode;
}
