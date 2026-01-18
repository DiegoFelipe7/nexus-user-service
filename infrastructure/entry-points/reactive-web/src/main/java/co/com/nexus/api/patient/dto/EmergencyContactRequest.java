package co.com.nexus.api.patient.dto;

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
public class EmergencyContactRequest {
    
    @NotBlank(message = "El nombre del contacto de emergencia no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String emergencyContactName;
    
    @NotBlank(message = "El teléfono del contacto de emergencia no puede estar vacío")
    @Pattern(regexp = "^\\+?[0-9\\s-]{10,20}$", message = "El número de teléfono debe tener entre 10 y 20 caracteres")
    private String emergencyContactPhone;
    
    @NotBlank(message = "La relación no puede estar vacía")
    @Size(max = 50, message = "La relación no puede exceder 50 caracteres")
    private String relationship;
    
    @Size(max = 500, message = "Las notas no pueden exceder 500 caracteres")
    private String emergencyNotes;
}
