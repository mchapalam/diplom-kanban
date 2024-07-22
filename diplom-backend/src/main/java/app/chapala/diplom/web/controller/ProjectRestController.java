package app.chapala.diplom.web.controller;

import app.chapala.diplom.security.AppUserDetails;
import app.chapala.diplom.service.impl.DatabaseJobService;
import app.chapala.diplom.service.impl.DatabaseProjectService;
import app.chapala.diplom.web.mapper.JobMapper;
import app.chapala.diplom.web.mapper.ProjectMapper;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.ProjectResponse;
import app.chapala.diplom.web.model.UpsertProjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/app/project")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin
public class ProjectRestController {
    private final DatabaseProjectService projectService;


    @GetMapping("/user")
    public List<ProjectResponse> findByUser(@AuthenticationPrincipal AppUserDetails appUserDetails){
        return projectService.findByUser(appUserDetails.getId());
    }

    @GetMapping
    public List<ProjectResponse> findAll(){
        return projectService.findAll();
    }

    @PostMapping
    public ProjectResponse save(@RequestBody UpsertProjectRequest projectRequest){
        return projectService.save(projectRequest);
    }

    @PutMapping
    public ProjectResponse update(@RequestParam UUID projectId, @RequestBody UpsertProjectRequest projectRequest){
        return projectService.update(projectId, projectRequest);
    }

    @DeleteMapping
    public void delete(@RequestParam UUID projectId){
        projectService.deleteById(projectId);
    }

    @PostMapping("/{projectId}/users")
    public ProjectResponse addUsersToProject(
            @PathVariable UUID projectId,
            @RequestBody List<UUID> userIds) {
        ProjectResponse updatedProject = projectService.addUsersToProject(projectId, userIds);
        return updatedProject;
    }

    @PostMapping("/{projectId}/jobs")
    public ProjectResponse addJobsToProject(
            @PathVariable UUID projectId,
            @RequestBody List<UUID> jobsIds) {
        ProjectResponse updatedProject = projectService.addJobsToProject(projectId, jobsIds);
        return updatedProject;
    }
}
