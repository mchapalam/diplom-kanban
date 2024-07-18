package app.chapala.diplom.service.impl;

import app.chapala.diplom.configuration.model.AuthResponse;
import app.chapala.diplom.model.User;
import app.chapala.diplom.repository.UserRepository;
import app.chapala.diplom.service.UserService;
import app.chapala.diplom.utilis.BeanUtils;
import app.chapala.diplom.web.mapper.UserMapper;
import app.chapala.diplom.web.model.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseUserService implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::userToResponse)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Пользователь не найден", id)));
    }

    @Override
    public UserResponse save(User user) {
        return userMapper.userToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(User user) {
        User existedUser = userRepository.findById(user.getId()).get();
        BeanUtils.copyNonNullProperties(user, existedUser);

        return userMapper.userToResponse(userRepository.save(existedUser));
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void batchInsert(List<User> users) {

    }

    @Override
    public List<UserResponse> getRandomUsers(Long count) {
        return userRepository.getRandomCountUsers(count)
                .stream().map(userMapper::userToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> filterByWages() {
        return userRepository.filterByWages()
                .stream().map(userMapper::userToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> filterByWagesDesc() {
        return userRepository.filterByWagesDesc()
                .stream().map(userMapper::userToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public AuthResponse findUserByUsername(String username) {
        return userMapper.userToFirstAuthResponse(userRepository.findByUsername(username).get());
    }


}
