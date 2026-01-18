package co.com.nexus.model.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmergencyInfoModel {
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String relationship;
    private String emergencyNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
