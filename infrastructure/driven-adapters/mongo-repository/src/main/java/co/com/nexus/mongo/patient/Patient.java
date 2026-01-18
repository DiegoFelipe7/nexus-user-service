package co.com.nexus.mongo.patient;

import co.com.nexus.model.patient.model.EmergencyInfoModel;
import co.com.nexus.model.patient.model.MedicalInfoModel;
import co.com.nexus.model.patient.model.PersonalInfoModel;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;
    private PersonalInfoModel personalInfo;
    private MedicalInfoModel medicalInfo;
    private List<EmergencyInfoModel> emergencyContacts;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
