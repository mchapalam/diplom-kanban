package app.chapala.diplom.web.mapper;

import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.Project;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.ProjectResponse;
import app.chapala.diplom.web.model.UpsertJobRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;


@Mapper(componentModel = "spring")
public interface JobMapper {
    Job requestToJob(UpsertJobRequest request);

    Job requestToJob(UUID id, UpsertJobRequest request);

    Job responseToJob(JobResponse response);

    @Mapping(source = "project.id", target = "projectId")
    JobResponse jobToResponse(Job job);

}
