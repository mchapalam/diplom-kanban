package app.chapala.diplom.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;

}
