package pl.cpapp.back.service;

import org.springframework.stereotype.Service;
import pl.cpapp.back.data.request.ConversationRequest;
import pl.cpapp.back.model.Conversation;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.ConversationRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserService userService;

    public ConversationService(ConversationRepository conversationRepository, UserService userService) {
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    public Conversation createFromRequest(ConversationRequest request) {
        User userA = userService.getById(request.getUserAId());
        User userB = userService.getById(request.getUserBId());

        Conversation conversation = new Conversation();
        conversation.setUserA(userA);
        conversation.setUserB(userB);
        conversation.setCreationDate(Timestamp.from(Instant.now()));
        conversation.setModificationDate(Timestamp.from(Instant.now()));
        conversation.setMessages(new ArrayList<>());

        return conversationRepository.save(conversation);
    }

    public Conversation getById(Long id) {
        return conversationRepository.getOne(id);
    }

    public List<Conversation> getAll() {
        return conversationRepository.findAll();
    }
}
