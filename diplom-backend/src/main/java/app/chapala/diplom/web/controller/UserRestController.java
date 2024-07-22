package app.chapala.diplom.web.controller;

import app.chapala.diplom.service.impl.DatabaseUserService;
import app.chapala.diplom.web.mapper.UserMapper;
import app.chapala.diplom.web.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/app/user")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('USER')")
@CrossOrigin
public class UserRestController {
    private final DatabaseUserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping("/id_user={id}")
    public UserResponse findIdUser(@PathVariable UUID id){
        return userService.findById(id);
    }

    @GetMapping("/filter_user_by_wages")
    public List<UserResponse> filterByWages(){
        return userService.filterByWages();
    }

    @GetMapping("/filter_user_by_wages_desc")
    public List<UserResponse> filterByWagesDesc(){
        return userService.filterByWagesDesc();
    }
}
