package app.chapala.diplom.web.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private Float wages;
    private List<JobResponse> jobs;
}
