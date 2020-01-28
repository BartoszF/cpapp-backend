package pl.cpapp.back.data.response;

import lombok.*;
import pl.cpapp.back.model.Conversation;
import pl.cpapp.back.model.Message;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationResponse {
    private Long id;
    private Long userAId;
    private Long userBId;
    private Timestamp modificationDate;
    private Timestamp creationDate;
    private Optional<MessageResponse> lastMessage;

    public static Optional<ConversationResponse> from(Conversation conversation) {
        return Optional.of(builder().id(conversation.getId()).userAId(conversation.getUserA().getId()).userBId(conversation.getUserB().getId())
                .modificationDate(conversation.getModificationDate())
                .lastMessage(MessageResponse.from(conversation.getMessages().stream().sorted(Comparator.comparing(Message::getDate)).findFirst())).build());
    }
}
