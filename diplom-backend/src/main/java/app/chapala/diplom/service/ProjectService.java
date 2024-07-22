package app.chapala.diplom.service;

import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.Project;
import app.chapala.diplom.web.model.ProjectResponse;
import app.chapala.diplom.web.model.UpsertProjectRequest;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    List<ProjectResponse> findAll();

    ProjectResponse findById(UUID id);

    List<ProjectResponse>  findByUser(UUID id);

    ProjectResponse save(UpsertProjectRequest projectRequest);

    ProjectResponse update(UUID projectId, UpsertProjectRequest projectRequest);

    void deleteById(UUID id);

    ProjectResponse addUsersToProject(UUID id, List<UUID> usersId);

    ProjectResponse addJobsToProject(UUID id, List<UUID> jobsId);
}
