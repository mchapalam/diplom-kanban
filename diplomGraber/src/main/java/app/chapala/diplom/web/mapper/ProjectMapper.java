package app.chapala.diplom.web.mapper;

import app.chapala.diplom.model.Project;
import app.chapala.diplom.web.model.ProjectResponse;
import app.chapala.diplom.web.model.UpsertProjectRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "jobs", target = "jobs")
    @Mapping(source = "users", target = "users")
    ProjectResponse projectToProjectResponse(Project project);

    Project toProject (UpsertProjectRequest projectRequest);
}
