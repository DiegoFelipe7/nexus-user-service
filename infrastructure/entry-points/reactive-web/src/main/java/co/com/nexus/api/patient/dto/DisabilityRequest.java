package co.com.nexus.api.patient.dto;

import co.com.nexus.model.shared.enums.DisabilityLevel;
import co.com.nexus.model.shared.enums.DisabilityType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DisabilityRequest {
    
    @NotNull(message = "El tipo de discapacidad no puede estar vacío")
    private DisabilityType type;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;
    
    private DisabilityLevel level;
    
    @Builder.Default
    private boolean requiresAssistance = false;
}
