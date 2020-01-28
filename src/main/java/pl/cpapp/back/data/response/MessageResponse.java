package pl.cpapp.back.data.response;

import lombok.*;
import pl.cpapp.back.model.Message;

import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private Long id;
    private Long conversationId;
    private Long senderId;
    private String text;
    private Timestamp date;

    public static Optional<MessageResponse> from(Optional<Message> message) {
        if (message.isPresent()) {
            Message msg = message.get();
            return Optional.of(builder().id(msg.getId()).senderId(msg.getSender().getId()).conversationId(msg.getConversation().getId()).text(msg.getText()).date(msg.getDate()).build());
        }
        return Optional.empty();
    }
}
