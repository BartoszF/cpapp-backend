package pl.cpapp.back.service;

import org.springframework.stereotype.Service;
import pl.cpapp.back.data.request.MessageRequest;
import pl.cpapp.back.model.Message;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.MessageRepository;
import pl.cpapp.back.security.UserPrincipal;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public Message create(MessageRequest messageRequest, UserPrincipal currentUser, Long conversationId) {
        Message message = new Message();
        message.setText(messageRequest.getText());

        User user = userService.getById(currentUser.getId());

        message.setSender(user);
        message.setDate(Timestamp.from(Instant.now()));

        return messageRepository.save(message);
    }
}
