package co.com.nexus.api.professionalprofile.mapper;

import co.com.nexus.api.professionalprofile.dto.ProfessionalProfileRequest;
import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.model.shared.pagination.QueryParams;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProfessionalProfileMapper {

    private ProfessionalProfileMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ProfessionalProfileModel mapToModel(ProfessionalProfileRequest request , UUID userId) {
        return ProfessionalProfileModel.builder()
                .specialty(request.getSpecialty())
                .medicalLicense(request.getMedicalLicense())
                .licenseCountry(request.getLicenseCountry())
                .licenseExpirationDate(request.getLicenseExpirationDate())
                .yearsOfExperience(request.getYearsOfExperience())
                .professionalBio(request.getProfessionalBio())
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
