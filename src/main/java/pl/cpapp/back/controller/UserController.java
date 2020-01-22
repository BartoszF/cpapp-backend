package pl.cpapp.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cpapp.back.data.response.ConversationResponse;
import pl.cpapp.back.data.response.UserResponse;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.UserRepository;
import pl.cpapp.back.security.CurrentUser;
import pl.cpapp.back.security.UserPrincipal;
import pl.cpapp.back.service.ConversationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final ConversationService conversationService;

    public UserController(UserRepository userRepository, ConversationService conversationService) {
        this.userRepository = userRepository;
        this.conversationService = conversationService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());
        return new ResponseEntity<>(UserResponse.from(user), HttpStatus.OK);
    }

    @GetMapping("/me/converstaions")
    public ResponseEntity<List<ConversationResponse>> getUserConversations(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());

        List<ConversationResponse> conversations = user.getConversations().stream().map(conversation -> ConversationResponse.from(conversation).get()).collect(Collectors.toList());
        return ResponseEntity.ok(conversations);
    }
}
