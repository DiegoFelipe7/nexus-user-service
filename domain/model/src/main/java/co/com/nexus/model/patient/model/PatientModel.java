package co.com.nexus.model.patient.model;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class PatientModel {
    private String id;
    private PersonalInfoModel personalInfo;
    private MedicalInfoModel medicalInfo;
    private List<EmergencyInfoModel> emergencyContacts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
