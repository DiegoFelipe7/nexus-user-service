package co.com.nexus.model.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class AllergiesModel {
    private String substance;
    private String reaction;
    private String criticized;
    private String notes;
}
