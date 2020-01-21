package pl.cpapp.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cpapp.back.data.response.UserResponse;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.UserRepository;
import pl.cpapp.back.security.CurrentUser;
import pl.cpapp.back.security.UserPrincipal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());
        return new ResponseEntity<>(UserResponse.from(user), HttpStatus.OK);
    }
}
