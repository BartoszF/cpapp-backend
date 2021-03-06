package pl.cpapp.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cpapp.back.data.request.AdminUserRequest;
import pl.cpapp.back.data.request.ContactRequest;
import pl.cpapp.back.data.response.ConversationResponse;
import pl.cpapp.back.data.response.UserResponse;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.UserRepository;
import pl.cpapp.back.security.CurrentUser;
import pl.cpapp.back.security.UserPrincipal;
import pl.cpapp.back.service.ConversationService;
import pl.cpapp.back.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ConversationService conversationService;

    public UserController(UserRepository userRepository, UserService userService, ConversationService conversationService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.conversationService = conversationService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());
        return new ResponseEntity<>(UserResponse.from(user), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> UserResponse.from(user)).collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<UserResponse> createUser(@RequestBody AdminUserRequest request) {
        return ResponseEntity.ok(UserResponse.from(userService.createUser(request)));
    }

    @GetMapping("/me/conversations")
    public ResponseEntity<List<ConversationResponse>> getUserConversations(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());

        List<ConversationResponse> conversations = user.getConversations().stream().map(conversation -> ConversationResponse.from(conversation).get()).collect(Collectors.toList());
        return ResponseEntity.ok(conversations);
    }

    @PostMapping("/me/contact")
    public ResponseEntity<User> addContact(@CurrentUser UserPrincipal currentUser, @RequestBody ContactRequest body) {
        User user = userRepository.getOne(currentUser.getId());

        return ResponseEntity.of(userService.addContact(user, body.getContactNumber()));
    }
}
