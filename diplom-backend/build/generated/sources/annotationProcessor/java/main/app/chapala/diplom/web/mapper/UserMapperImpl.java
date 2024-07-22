package app.chapala.diplom.web.mapper;

import app.chapala.diplom.configuration.model.AuthResponse;
import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.User;
import app.chapala.diplom.web.model.CustomJobForUser;
import app.chapala.diplom.web.model.JobResponse;
import app.chapala.diplom.web.model.UpsertUserRequest;
import app.chapala.diplom.web.model.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T19:59:20+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User requestToUser(UpsertUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        return user.build();
    }

    @Override
    public UserResponse userToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setJobs( jobListToJobResponseList( user.getJobs() ) );
        userResponse.setId( user.getId() );
        userResponse.setFirstName( user.getFirstName() );
        userResponse.setLastName( user.getLastName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setWages( user.getWages() );

        return userResponse;
    }

    @Override
    public CustomJobForUser jobForUserToResponse(Job job) {
        if ( job == null ) {
            return null;
        }

        CustomJobForUser customJobForUser = new CustomJobForUser();

        customJobForUser.setId( job.getId() );
        customJobForUser.setTitle( job.getTitle() );
        customJobForUser.setType( job.getType() );

        return customJobForUser;
    }

    @Override
    public User responseToUser(UserResponse user) {
        if ( user == null ) {
            return null;
        }

        User.UserBuilder user1 = User.builder();

        user1.id( user.getId() );
        user1.firstName( user.getFirstName() );
        user1.lastName( user.getLastName() );
        user1.wages( user.getWages() );
        user1.username( user.getUsername() );
        user1.jobs( jobResponseListToJobList( user.getJobs() ) );

        return user1.build();
    }

    @Override
    public AuthResponse userToFirstAuthResponse(User user) {
        if ( user == null ) {
            return null;
        }

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();

        authResponse.id( user.getId() );
        authResponse.username( user.getUsername() );
        authResponse.email( user.getEmail() );

        return authResponse.build();
    }

    @Override
    public AuthResponse userResponseToAuthResponse(UserResponse user) {
        if ( user == null ) {
            return null;
        }

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();

        authResponse.id( user.getId() );
        authResponse.username( user.getUsername() );

        return authResponse.build();
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

    protected Job jobResponseToJob(JobResponse jobResponse) {
        if ( jobResponse == null ) {
            return null;
        }

        Job.JobBuilder job = Job.builder();

        job.id( jobResponse.getId() );
        job.title( jobResponse.getTitle() );
        job.dateCreate( jobResponse.getDateCreate() );
        job.dateUpdate( jobResponse.getDateUpdate() );
        job.info( jobResponse.getInfo() );
        job.expectedTime( jobResponse.getExpectedTime() );
        job.actualTime( jobResponse.getActualTime() );
        job.type( jobResponse.getType() );

        return job.build();
    }

    protected List<Job> jobResponseListToJobList(List<JobResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<Job> list1 = new ArrayList<Job>( list.size() );
        for ( JobResponse jobResponse : list ) {
            list1.add( jobResponseToJob( jobResponse ) );
        }

        return list1;
    }
}
