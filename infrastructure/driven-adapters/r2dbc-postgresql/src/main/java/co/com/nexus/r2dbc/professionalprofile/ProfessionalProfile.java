package co.com.nexus.r2dbc.professionalprofile;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "professional_profiles")
public class ProfessionalProfile {
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
