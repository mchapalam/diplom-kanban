package app.chapala.diplom.service.impl;

import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.Project;
import app.chapala.diplom.model.User;
import app.chapala.diplom.repository.JobRepository;
import app.chapala.diplom.repository.ProjectRepository;
import app.chapala.diplom.repository.UserRepository;
import app.chapala.diplom.service.ProjectService;
import app.chapala.diplom.utilis.BeanUtils;
import app.chapala.diplom.web.mapper.ProjectMapper;
import app.chapala.diplom.web.model.ProjectResponse;
import app.chapala.diplom.web.model.UpsertProjectRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DatabaseProjectService implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream().map(projectMapper::projectToProjectResponse)
                .toList();
    }

    @Override
    public ProjectResponse findById(UUID id) {
        return projectRepository.findById(id)
                .stream().map(projectMapper::projectToProjectResponse)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Проект не найден", id)));
    }

    @Override
    public List<ProjectResponse> findByUser(UUID id) {

        return projectRepository.findProjectsByUsersId(id)
                .stream().map(projectMapper::projectToProjectResponse).toList();
    }

    @Override
    public ProjectResponse save(UpsertProjectRequest projectRequest) {
        Project project = projectMapper.toProject(projectRequest);
        return projectMapper.projectToProjectResponse(
                projectRepository.save(project));
    }

    @Override
    public ProjectResponse update(UUID projectId, UpsertProjectRequest projectRequest) {
        Project existedProject = projectRepository.findById(projectId).get();
        BeanUtils.copyNonNullProperties(projectRequest, existedProject);

        return projectMapper.projectToProjectResponse(
                projectRepository.save(existedProject)
        );
    }

    @Override
    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectResponse addUsersToProject(UUID id, List<UUID> usersId) {
        Project project = projectRepository.findById(id).get();

        List<User> users = userRepository.findAllById(usersId);
        project.addUsers(users);

        Project updatedProject = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(updatedProject);
    }

    @Override
    public ProjectResponse addJobsToProject(UUID id, List<UUID> jobsId) {
        Project project = projectRepository.findById(id).get();

        List<Job> jobs = jobRepository.findAllById(jobsId);
        project.addJobs(jobs);

        Project updatedProject = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(updatedProject);
    }
}
