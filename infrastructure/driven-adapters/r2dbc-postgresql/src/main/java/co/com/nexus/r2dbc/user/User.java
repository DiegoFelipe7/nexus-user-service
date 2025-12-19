package co.com.nexus.r2dbc.user;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String firstName;
    private UUID userId;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String searchKey;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
