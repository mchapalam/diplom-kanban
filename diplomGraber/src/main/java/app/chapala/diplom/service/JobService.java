package app.chapala.diplom.service;

import app.chapala.diplom.model.Job;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.UpsertJobRequest;

import java.util.List;
import java.util.UUID;

public interface JobService {
    List<JobResponse> findAll();

    JobResponse findById(UUID id);

    JobResponse save(UpsertJobRequest upsertJobRequest);

    JobResponse save(Job job);

    JobResponse update(Job job);

    void deleteById(UUID id);

    List<JobResponse> printByTypeNew(UUID projectId);

    List<JobResponse> printByTypeToDo(UUID projectId);

    List<JobResponse> printByTypeInProgress(UUID projectId);

    List<JobResponse> printByTypeTesting(UUID projectId);

    List<JobResponse> printByTypeDone(UUID projectId);

    void setType(UUID id);

    void setBackType(UUID id);

    List<JobResponse> findJobListByIdProject(UUID projectId);

    List<JobResponse> findJobListByStage(UUID projectId, String stage);
}
