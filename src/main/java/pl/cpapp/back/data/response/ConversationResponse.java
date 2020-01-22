package pl.cpapp.back.data.response;

import lombok.*;
import pl.cpapp.back.model.Conversation;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private List<MessageResponse> messages;

    public static Optional<ConversationResponse> from(Conversation conversation) {
        return Optional.of(builder().id(conversation.getId()).userAId(conversation.getUserA().getId()).userBId(conversation.getUserB().getId())
                .modificationDate(conversation.getModificationDate())
                .messages(conversation.getMessages().stream().map(msg -> MessageResponse.from(msg)).collect(Collectors.toList())).build());
    }
}
