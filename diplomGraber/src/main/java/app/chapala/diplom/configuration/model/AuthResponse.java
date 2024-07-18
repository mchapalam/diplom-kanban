package app.chapala.diplom.configuration.model;

import app.chapala.diplom.web.model.ProjectResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private UUID id;
    private String token;
    private String refreshToken;
    private String username;
    private String email;
    private List<String> roles;
}
