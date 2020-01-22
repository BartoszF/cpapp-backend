package pl.cpapp.back.data.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationRequest {
    private Long userAId;
    private Long userBId;
}
