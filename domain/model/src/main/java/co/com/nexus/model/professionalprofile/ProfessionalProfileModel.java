package co.com.nexus.model.professionalprofile;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProfessionalProfileModel {
    private UUID id;
    private String specialty;
    private String medicalLicense;
    private String licenseCountry;
    private LocalDate licenseExpirationDate;
    private Integer yearsOfExperience;
    private String professionalBio;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
