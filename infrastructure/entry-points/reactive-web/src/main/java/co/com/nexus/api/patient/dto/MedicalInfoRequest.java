package co.com.nexus.api.patient.dto;

import co.com.nexus.model.shared.enums.Blood;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MedicalInfoRequest {
    
    private Blood bloodType;
    
    @Builder.Default
    private boolean hasAllergies = false;
    
    @Valid
    private List<AllergyRequest> allergies;
    
    @Builder.Default
    private boolean hasChronicDiseases = false;
    
    @Valid
    private List<ChronicDiseaseRequest> chronicDiseases;
    
    @Builder.Default
    private boolean hasDisabilities = false;
    
    @Valid
    private List<DisabilityRequest> disabilities;
    
    private String notes;
}
