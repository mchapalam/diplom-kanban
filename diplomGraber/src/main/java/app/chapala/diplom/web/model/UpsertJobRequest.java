package app.chapala.diplom.web.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpsertJobRequest {
    private UUID id;
    private String title;
    private Long expectedTime;
    private Long actualTime;
    private UUID projectId;
    private String info;
}
