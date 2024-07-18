package app.chapala.diplom.web.mapper;

import app.chapala.diplom.configuration.model.AuthResponse;
import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.User;
import app.chapala.diplom.web.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToUser(UpsertUserRequest request);
    @Mapping(source = "jobs", target = "jobs")
    UserResponse userToResponse(User user);

    CustomJobForUser jobForUserToResponse(Job job);

    User responseToUser(UserResponse user);

    AuthResponse userToFirstAuthResponse(User user);

    AuthResponse userResponseToAuthResponse(UserResponse user);
}
