package co.com.nexus.model.user;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserModel {
    private UUID id;
    private String firstName;
    private UUID userId;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String searchKey;
    private String avatarUrl;
    private String birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public String getSearchKey() {
        return (firstName + " " + lastName + " " + email + " " + phoneNumber).toLowerCase();
    }
}
