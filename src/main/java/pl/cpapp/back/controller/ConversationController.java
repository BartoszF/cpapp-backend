package pl.cpapp.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cpapp.back.data.request.ConversationRequest;
import pl.cpapp.back.data.request.MessageRequest;
import pl.cpapp.back.data.response.ConversationResponse;
import pl.cpapp.back.data.response.MessageResponse;
import pl.cpapp.back.security.CurrentUser;
import pl.cpapp.back.security.UserPrincipal;
import pl.cpapp.back.service.ConversationService;
import pl.cpapp.back.service.MessageService;

@RestController
@RequestMapping("/api/conversation")
public class ConversationController {

    private final ConversationService conversationService;
    private final MessageService messageService;

    public ConversationController(ConversationService conversationService, MessageService messageService) {
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<ConversationResponse> createResponse(@RequestBody ConversationRequest body) {
        return ResponseEntity.of(ConversationResponse.from(conversationService.createFromRequest(body)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationResponse> getResponse(@PathVariable("id") Long id) {
        return ResponseEntity.of(ConversationResponse.from(conversationService.getById(id)));
    }

    @PostMapping("/{id}/message")
    public ResponseEntity<MessageResponse> createMessage(@CurrentUser UserPrincipal currentUser, @RequestBody MessageRequest message, @PathVariable("id") Long id) {
        return ResponseEntity.ok(MessageResponse.from(messageService.create(message, currentUser, id)));
    }
}
