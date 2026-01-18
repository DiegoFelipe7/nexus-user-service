package co.com.nexus.model.patient.model;

import co.com.nexus.model.shared.enums.Blood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class MedicalInfoModel {
    private Blood bloodType;
    private boolean hasAllergies;
    private List<AllergiesModel> allergies;
    private boolean hasChronicDiseases;
    private List<ChronicDiseaseModel> chronicDiseases;
    private boolean hasDisabilities;
    private List<DisabilityModel> disabilities;
    private String notes;
}
