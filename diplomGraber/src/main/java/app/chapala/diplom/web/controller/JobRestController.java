package app.chapala.diplom.web.controller;

import app.chapala.diplom.service.impl.DatabaseJobService;
import app.chapala.diplom.web.mapper.JobMapper;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.UpsertJobRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/app/job")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin
public class JobRestController {
    private final DatabaseJobService jobService;
    private final JobMapper jobMapper;

    @GetMapping
    public List<JobResponse> findAll(){
        return jobService.findAll();
    }

    @PostMapping
    public JobResponse create (@RequestBody UpsertJobRequest request){
        return jobService.save(request);
    }

    @PutMapping("/{id}")
    public JobResponse update(@PathVariable("id") UUID id,
                              @RequestBody UpsertJobRequest request){
        return jobService.update(jobMapper.requestToJob(id, request));
    }

    @GetMapping("/next_stage_job={id}")
    public void nextStage(@PathVariable("id") UUID id){
        jobService.setType(id);
    }

    @GetMapping("/back_stage_job={id}")
    public void backStage(@PathVariable("id") UUID id){
        jobService.setBackType(id);
    }

    @GetMapping("/id_job={id}")
    public JobResponse findIdJob(@PathVariable UUID id){
        return jobService.findById(id);
    }

    @GetMapping("/id_project={id}")
    public List<JobResponse>findJobListByIdProject(@PathVariable UUID id){
        return jobService.findJobListByIdProject(id);
    }

    @GetMapping("/id_project={id}/stage={stage}")
    public List<JobResponse>findJobListByStage(@PathVariable UUID id,
                                               @PathVariable String stage){
        return jobService.findJobListByStage(id, stage);
    }

}

