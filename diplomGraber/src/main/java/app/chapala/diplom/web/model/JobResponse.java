package app.chapala.diplom.web.model;

import app.chapala.diplom.model.JobType;
import app.chapala.diplom.model.Project;
import app.chapala.diplom.model.User;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class JobResponse {
    private UUID id;
    private String title;
    private Instant dateCreate;
    private Instant dateUpdate;
    private JobType type;
    private Long expectedTime;
    private Long actualTime;
    private UUID projectId;
    private String info;

}
