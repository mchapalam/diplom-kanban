package app.chapala.diplom.web.model;

import app.chapala.diplom.model.JobType;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class CustomJobForUser {
    private UUID id;
    private String title;
    private JobType type;
    private Long projectId;
}
