package co.com.nexus.r2dbc.professionalprofile.mapper;

import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.r2dbc.professionalprofile.ProfessionalProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProfessionalProfileMapper {


    private ProfessionalProfileMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ProfessionalProfileModel mapToModel(ProfessionalProfile professionalProfile) {
        return ProfessionalProfileModel.builder()
                .id(professionalProfile.getId())
                .specialty(professionalProfile.getSpecialty())
                .medicalLicense(professionalProfile.getMedicalLicense())
                .licenseCountry(professionalProfile.getLicenseCountry())
                .licenseExpirationDate(professionalProfile.getLicenseExpirationDate())
                .yearsOfExperience(professionalProfile.getYearsOfExperience())
                .professionalBio(professionalProfile.getProfessionalBio())
                .userId(professionalProfile.getUserId())
                .createdAt(professionalProfile.getCreatedAt())
                .updatedAt(professionalProfile.getUpdatedAt())
                .build();
    }

    public static ProfessionalProfile mapToEntity(ProfessionalProfileModel professionalProfileModel) {
        return ProfessionalProfile.builder()
                .id(professionalProfileModel.getId())
                .specialty(professionalProfileModel.getSpecialty())
                .medicalLicense(professionalProfileModel.getMedicalLicense())
                .licenseCountry(professionalProfileModel.getLicenseCountry())
                .licenseExpirationDate(professionalProfileModel.getLicenseExpirationDate())
                .yearsOfExperience(professionalProfileModel.getYearsOfExperience())
                .professionalBio(professionalProfileModel.getProfessionalBio())
                .userId(professionalProfileModel.getUserId())
                .createdAt(professionalProfileModel.getCreatedAt())
                .updatedAt(professionalProfileModel.getUpdatedAt())
                .build();
    }
}
