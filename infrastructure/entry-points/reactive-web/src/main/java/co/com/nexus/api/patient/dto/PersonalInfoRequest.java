package co.com.nexus.api.patient.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PersonalInfoRequest {
    
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String firstName;
    
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
    private String lastName;
    
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;
    
    @NotBlank(message = "El tipo de documento no puede estar vacío")
    @Pattern(regexp = "^(CC|CE|TI|PA|NIT)$", message = "El tipo de documento debe ser CC, CE, TI, PA o NIT")
    private String documentType;
    
    @NotBlank(message = "El número de documento no puede estar vacío")
    @Size(min = 5, max = 20, message = "El número de documento debe tener entre 5 y 20 caracteres")
    private String documentNumber;
    
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "^\\+?[0-9\\s-]{10,20}$", message = "El número de teléfono debe tener entre 10 y 20 caracteres")
    private String phoneNumber;
    
    @NotBlank(message = "La fecha de nacimiento no puede estar vacía")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de nacimiento debe estar en formato YYYY-MM-DD")
    private String dateOfBirth;
    
    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String address;
    
    @NotBlank(message = "La ciudad no puede estar vacía")
    @Size(max = 100, message = "La ciudad no puede exceder 100 caracteres")
    private String city;
    
    @NotBlank(message = "El país no puede estar vacío")
    @Size(max = 100, message = "El país no puede exceder 100 caracteres")
    private String country;
    
    @Size(max = 10, message = "El código postal no puede exceder 10 caracteres")
    private String postalCode;
}
