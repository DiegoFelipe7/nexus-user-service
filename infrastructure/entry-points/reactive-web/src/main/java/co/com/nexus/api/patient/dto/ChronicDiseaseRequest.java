package co.com.nexus.api.patient.dto;

import co.com.nexus.model.shared.enums.ChronicDiseaseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChronicDiseaseRequest {
    
    @NotBlank(message = "El nombre de la enfermedad no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String name;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;
    
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de diagnóstico debe estar en formato YYYY-MM-DD")
    private String diagnosedAt;
    
    private ChronicDiseaseStatus status;
}
