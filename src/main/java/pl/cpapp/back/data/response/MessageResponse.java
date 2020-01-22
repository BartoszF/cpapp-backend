package pl.cpapp.back.data.response;

import lombok.*;
import pl.cpapp.back.model.Message;

import java.sql.Timestamp;

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

    public static MessageResponse from(Message message) {
        return builder().id(message.getId()).senderId(message.getSender().getId()).conversationId(message.getConversation().getId()).text(message.getText()).date(message.getDate()).build();
    }
}
