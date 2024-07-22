package app.chapala.diplom.service;

import app.chapala.diplom.configuration.model.AuthResponse;
import app.chapala.diplom.model.User;
import app.chapala.diplom.web.model.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponse> findAll();

    UserResponse findById(UUID id);

    UserResponse save(User user);

    UserResponse update(User user);

    void deleteById(UUID id);

    void batchInsert(List<User> users);

    List<UserResponse> getRandomUsers(Long count);

    List<UserResponse> filterByWages();

    List<UserResponse> filterByWagesDesc();

    boolean existsByUsername(String username);

    AuthResponse findUserByUsername(String username);
}
