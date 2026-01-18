package co.com.nexus.api.patient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AllergyRequest {
    
    @NotBlank(message = "La sustancia alergénica no puede estar vacía")
    @Size(max = 100, message = "La sustancia no puede exceder 100 caracteres")
    private String substance;
    
    @Size(max = 200, message = "La reacción no puede exceder 200 caracteres")
    private String reaction;
    
    @Size(max = 100, message = "La criticidad no puede exceder 100 caracteres")
    private String criticized;
    
    @Size(max = 500, message = "Las notas no pueden exceder 500 caracteres")
    private String notes;
}
