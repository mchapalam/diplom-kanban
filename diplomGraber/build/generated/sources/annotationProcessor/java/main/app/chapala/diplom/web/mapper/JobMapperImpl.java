package app.chapala.diplom.web.mapper;

import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.Project;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.UpsertJobRequest;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T19:59:20+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public Job requestToJob(UpsertJobRequest request) {
        if ( request == null ) {
            return null;
        }

        Job.JobBuilder job = Job.builder();

        job.id( request.getId() );
        job.title( request.getTitle() );
        job.info( request.getInfo() );
        job.expectedTime( request.getExpectedTime() );
        job.actualTime( request.getActualTime() );

        return job.build();
    }

    @Override
    public Job requestToJob(UUID id, UpsertJobRequest request) {
        if ( id == null && request == null ) {
            return null;
        }

        Job.JobBuilder job = Job.builder();

        if ( request != null ) {
            job.id( request.getId() );
            job.title( request.getTitle() );
            job.info( request.getInfo() );
            job.expectedTime( request.getExpectedTime() );
            job.actualTime( request.getActualTime() );
        }

        return job.build();
    }

    @Override
    public Job responseToJob(JobResponse response) {
        if ( response == null ) {
            return null;
        }

        Job.JobBuilder job = Job.builder();

        job.id( response.getId() );
        job.title( response.getTitle() );
        job.dateCreate( response.getDateCreate() );
        job.dateUpdate( response.getDateUpdate() );
        job.info( response.getInfo() );
        job.expectedTime( response.getExpectedTime() );
        job.actualTime( response.getActualTime() );
        job.type( response.getType() );

        return job.build();
    }

    @Override
    public JobResponse jobToResponse(Job job) {
        if ( job == null ) {
            return null;
        }

        JobResponse jobResponse = new JobResponse();

        jobResponse.setProjectId( jobProjectId( job ) );
        jobResponse.setId( job.getId() );
        jobResponse.setTitle( job.getTitle() );
        jobResponse.setDateCreate( job.getDateCreate() );
        jobResponse.setDateUpdate( job.getDateUpdate() );
        jobResponse.setType( job.getType() );
        jobResponse.setExpectedTime( job.getExpectedTime() );
        jobResponse.setActualTime( job.getActualTime() );
        jobResponse.setInfo( job.getInfo() );

        return jobResponse;
    }

    private UUID jobProjectId(Job job) {
        if ( job == null ) {
            return null;
        }
        Project project = job.getProject();
        if ( project == null ) {
            return null;
        }
        UUID id = project.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
