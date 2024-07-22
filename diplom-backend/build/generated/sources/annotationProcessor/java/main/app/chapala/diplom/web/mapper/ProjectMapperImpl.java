package app.chapala.diplom.web.mapper;

import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.Project;
import app.chapala.diplom.model.User;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.ProjectResponse;
import app.chapala.diplom.web.model.UpsertProjectRequest;
import app.chapala.diplom.web.model.UserResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T19:59:20+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectResponse projectToProjectResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectResponse.ProjectResponseBuilder projectResponse = ProjectResponse.builder();

        projectResponse.jobs( jobListToJobResponseList( project.getJobs() ) );
        projectResponse.users( userSetToUserResponseSet( project.getUsers() ) );
        projectResponse.id( project.getId() );
        projectResponse.title( project.getTitle() );

        return projectResponse.build();
    }

    @Override
    public Project toProject(UpsertProjectRequest projectRequest) {
        if ( projectRequest == null ) {
            return null;
        }

        Project.ProjectBuilder project = Project.builder();

        project.title( projectRequest.getTitle() );

        return project.build();
    }

    protected JobResponse jobToJobResponse(Job job) {
        if ( job == null ) {
            return null;
        }

        JobResponse jobResponse = new JobResponse();

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

    protected List<JobResponse> jobListToJobResponseList(List<Job> list) {
        if ( list == null ) {
            return null;
        }

        List<JobResponse> list1 = new ArrayList<JobResponse>( list.size() );
        for ( Job job : list ) {
            list1.add( jobToJobResponse( job ) );
        }

        return list1;
    }

    protected List<JobResponse> jobListToJobResponseList1(List<Job> list) {
        if ( list == null ) {
            return null;
        }

        List<JobResponse> list1 = new ArrayList<JobResponse>( list.size() );
        for ( Job job : list ) {
            list1.add( jobToJobResponse( job ) );
        }

        return list1;
    }

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setFirstName( user.getFirstName() );
        userResponse.setLastName( user.getLastName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setWages( user.getWages() );
        userResponse.setJobs( jobListToJobResponseList1( user.getJobs() ) );

        return userResponse;
    }

    protected Set<UserResponse> userSetToUserResponseSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserResponse> set1 = new LinkedHashSet<UserResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToUserResponse( user ) );
        }

        return set1;
    }
}
