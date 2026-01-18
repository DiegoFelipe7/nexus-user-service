package co.com.nexus.model.patient.model;


import co.com.nexus.model.shared.enums.DisabilityLevel;
import co.com.nexus.model.shared.enums.DisabilityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DisabilityModel {
    private DisabilityType type;
    private String description;
    private DisabilityLevel level;
    private boolean requiresAssistance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
