package co.com.nexus.api.patient.mapper;
import co.com.nexus.api.patient.dto.*;
import co.com.nexus.model.patient.model.*;
import co.com.nexus.model.shared.pagination.QueryParams;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PatientMapper {

    private PatientMapper() {
        throw new IllegalStateException("Utility class");
    }



    public static  PatientModel toModel(PatientRequest request) {
        return PatientModel.builder()
                .personalInfo(toPersonalInfoModel(request.getPersonalInfo()))
                .medicalInfo(toMedicalInfoModel(request.getMedicalInfo()))
                .emergencyContacts(toEmergencyContactsModel(request.getEmergencyContacts()))
                .build();
    }

    public static  PersonalInfoModel toPersonalInfoModel(PersonalInfoRequest request) {
        return PersonalInfoModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .build();
    }

    public static  MedicalInfoModel toMedicalInfoModel(MedicalInfoRequest request) {
        if (request == null) {
            return MedicalInfoModel.builder()
                    .hasAllergies(false)
                    .allergies(Collections.emptyList())
                    .hasChronicDiseases(false)
                    .chronicDiseases(Collections.emptyList())
                    .hasDisabilities(false)
                    .disabilities(Collections.emptyList())
                    .build();
        }

        return MedicalInfoModel.builder()
                .bloodType(request.getBloodType())
                .hasAllergies(request.isHasAllergies())
                .allergies(toAllergiesModel(request.getAllergies()))
                .hasChronicDiseases(request.isHasChronicDiseases())
                .chronicDiseases(toChronicDiseasesModel(request.getChronicDiseases()))
                .hasDisabilities(request.isHasDisabilities())
                .disabilities(toDisabilitiesModel(request.getDisabilities()))
                .notes(request.getNotes())
                .build();
    }

    public static  List<AllergiesModel> toAllergiesModel(List<AllergyRequest> requests) {
        return Optional.ofNullable(requests)
                .orElse(Collections.emptyList())
                .stream()
                .map(req -> AllergiesModel.builder()
                        .substance(req.getSubstance())
                        .reaction(req.getReaction())
                        .criticized(req.getCriticized())
                        .notes(req.getNotes())
                        .build())
                .collect(Collectors.toList());
    }

    public static  List<ChronicDiseaseModel> toChronicDiseasesModel(List<ChronicDiseaseRequest> requests) {
        return Optional.ofNullable(requests)
                .orElse(Collections.emptyList())
                .stream()
                .map(req -> ChronicDiseaseModel.builder()
                        .name(req.getName())
                        .description(req.getDescription())
                        .diagnosedAt(req.getDiagnosedAt() != null ? LocalDate.parse(req.getDiagnosedAt()) : null)
                        .status(req.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public static  List<DisabilityModel> toDisabilitiesModel(List<DisabilityRequest> requests) {
        return Optional.ofNullable(requests)
                .orElse(Collections.emptyList())
                .stream()
                .map(req -> DisabilityModel.builder()
                        .type(req.getType())
                        .description(req.getDescription())
                        .level(req.getLevel())
                        .requiresAssistance(req.isRequiresAssistance())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<EmergencyInfoModel> toEmergencyContactsModel(List<EmergencyContactRequest> requests) {
        return Optional.ofNullable(requests)
                .orElse(Collections.emptyList())
                .stream()
                .map(req -> EmergencyInfoModel.builder()
                        .emergencyContactName(req.getEmergencyContactName())
                        .emergencyContactPhone(req.getEmergencyContactPhone())
                        .relationship(req.getRelationship())
                        .emergencyNotes(req.getEmergencyNotes())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
    }

}
