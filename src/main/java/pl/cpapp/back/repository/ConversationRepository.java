package pl.cpapp.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cpapp.back.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
