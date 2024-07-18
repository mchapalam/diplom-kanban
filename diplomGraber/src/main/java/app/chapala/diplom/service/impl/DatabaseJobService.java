package app.chapala.diplom.service.impl;

import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.JobType;
import app.chapala.diplom.model.User;
import app.chapala.diplom.repository.JobRepository;
import app.chapala.diplom.repository.ProjectRepository;
import app.chapala.diplom.service.JobService;
import app.chapala.diplom.service.UserService;
import app.chapala.diplom.utilis.BeanUtils;
import app.chapala.diplom.web.mapper.JobMapper;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.UpsertJobRequest;
import app.chapala.diplom.web.model.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseJobService implements JobService{
    private final JobRepository jobRepository;
    private final ProjectRepository projectRepository;
    private final JobMapper jobMapper;

    @Override
    public List<JobResponse> findAll() {
        return jobRepository.findAll().stream()
                .map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse findById(UUID id) {
        return jobRepository.findById(id).map(jobMapper::jobToResponse)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Работа не найдена", id)))
                ;
    }


    @Override
    public JobResponse save(UpsertJobRequest upsertJobRequest) {
        Job job = jobMapper.requestToJob(upsertJobRequest);
        job.setProject(projectRepository.findById(upsertJobRequest.getProjectId()).get());
        job.setDateCreate(Instant.now());
        job.setType(JobType.New);

        return jobMapper.jobToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse save(Job job) {
        return jobMapper.jobToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse update(Job job) {
        Job existedJob = jobRepository.findById(job.getId()).get();
        BeanUtils.copyNonNullProperties(job, existedJob);

        return jobMapper.jobToResponse(jobRepository.save(job));
    }

    @Override
    public void deleteById(UUID id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<JobResponse> printByTypeNew(UUID projectId) {
        return jobRepository.getByTypeNew(projectId)
                .stream().map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> printByTypeToDo(UUID projectId) {
        return jobRepository.getByTypeToDo(projectId)
                .stream().map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> printByTypeInProgress(UUID projectId) {
        return jobRepository.getByTypeInProgress(projectId)
                .stream().map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public List<JobResponse> printByTypeTesting(UUID projectId) {
        return jobRepository.getByTypeTesting(projectId)
                .stream().map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> printByTypeDone(UUID projectId) {
        return jobRepository.getByTypeDone(projectId)
                .stream().map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void setType(UUID id) {
        Job job = jobRepository.findById(id).get();

        jobRepository
                .setType(JobType.nextType(job.getType()), job.getId());
    }

    @Override
    public void setBackType(UUID id) {
        Job job = jobRepository.findById(id).get();

        jobRepository
                .setType(JobType.backType(job.getType()), job.getId());
    }

    @Override
    public List<JobResponse> findJobListByIdProject(UUID projectId) {
        return jobRepository.findJobListByIdProject(projectId)
                .stream().map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<JobResponse> findJobListByStage(UUID projectId, String stage) {
        return jobRepository.findJobListByStage(projectId, stage)
                .stream().map(jobMapper::jobToResponse)
                .collect(Collectors.toList());
    }

}
