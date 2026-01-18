package co.com.nexus.mongo.patient.mapper;

import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.mongo.patient.Patient;

import java.time.LocalDateTime;

public final class PatientMapper {

    private PatientMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PatientModel mapToModel(Patient entity) {
        return PatientModel.builder()
                .id(entity.getId())
                .personalInfo(entity.getPersonalInfo())
                .medicalInfo(entity.getMedicalInfo())
                .emergencyContacts(entity.getEmergencyContacts())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static Patient mapToEntity(PatientModel model) {
        return Patient.builder()
                .id(model.getId())
                .personalInfo(model.getPersonalInfo())
                .medicalInfo(model.getMedicalInfo())
                .emergencyContacts(model.getEmergencyContacts())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }
}
