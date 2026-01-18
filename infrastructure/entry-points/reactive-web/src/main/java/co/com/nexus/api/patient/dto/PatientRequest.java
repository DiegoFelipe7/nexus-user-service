package co.com.nexus.api.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PatientRequest {
    
    @NotNull(message = "La información personal es obligatoria")
    @Valid
    private PersonalInfoRequest personalInfo;
    
    @Valid
    private MedicalInfoRequest medicalInfo;
    
    @Valid
    private List<EmergencyContactRequest> emergencyContacts;
}
