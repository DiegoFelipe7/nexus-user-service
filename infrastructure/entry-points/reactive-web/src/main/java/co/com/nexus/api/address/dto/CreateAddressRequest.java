package co.com.nexus.api.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAddressRequest {

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 100, message = "El nombre completo no puede exceder 100 caracteres")
    private String fullName;
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(
            regexp = "^[0-9+]{7,15}$",
            message = "El teléfono debe contener solo números y puede incluir el prefijo internacional"
    )
    private String phone;
    @NotBlank(message = "El país es obligatorio")
    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    private String country;
    @NotBlank(message = "El estado/departamento es obligatorio")
    @Size(max = 50, message = "El estado no puede exceder 50 caracteres")
    private String state;
    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    private String city;
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 150, message = "La dirección no puede exceder 150 caracteres")
    private String street;
    @Size(max = 50, message = "El apartamento no puede exceder 50 caracteres")
    private String apartment;
    @NotBlank(message = "El código postal es obligatorio")
    @Size(max = 15, message = "El código postal no puede exceder 15 caracteres")
    private String postalCode;
    @NotNull(message = "Debe indicar si la dirección es por defecto")
    private Boolean isDefault;

}
