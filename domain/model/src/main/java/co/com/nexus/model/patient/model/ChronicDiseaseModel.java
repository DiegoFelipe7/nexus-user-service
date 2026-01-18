package co.com.nexus.model.patient.model;

import co.com.nexus.model.shared.enums.ChronicDiseaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChronicDiseaseModel {

    private String name;
    private String description;
    private LocalDate diagnosedAt;
    private ChronicDiseaseStatus status;
}
