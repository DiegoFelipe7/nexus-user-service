package co.com.nexus.api.professionalprofile.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProfessionalProfileRequest {

    @NotBlank(message = "La especialidad no puede estar vacía")
    @Size(min = 2, max = 100, message = "La especialidad debe tener entre 2 y 100 caracteres")
    private String specialty;

    @NotBlank(message = "La licencia médica no puede estar vacía")
    @Size(min = 5, max = 50, message = "La licencia médica debe tener entre 5 y 50 caracteres")
    private String medicalLicense;

    @NotBlank(message = "El país de la licencia no puede estar vacío")
    @Size(min = 2, max = 50, message = "El país de la licencia debe tener entre 2 y 50 caracteres")
    private String licenseCountry;

    @NotNull(message = "La fecha de expiración de la licencia no puede estar vacía")
    @Future(message = "La fecha de expiración debe ser futura")
    private LocalDate licenseExpirationDate;

    @NotNull(message = "Los años de experiencia no pueden estar vacíos")
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    @Max(value = 70, message = "Los años de experiencia no pueden ser mayores a 70")
    private Integer yearsOfExperience;

    @NotBlank(message = "La biografía profesional no puede estar vacía")
    @Size(min = 50, max = 2000, message = "La biografía profesional debe tener entre 50 y 2000 caracteres")
    private String professionalBio;

}
