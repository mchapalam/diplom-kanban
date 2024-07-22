package app.chapala.diplom.web.model;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {
    private UUID id;
    private String title;
    private List<JobResponse> jobs;
    private Set<UserResponse> users;
}
