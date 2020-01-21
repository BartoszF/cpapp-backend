package pl.cpapp.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cpapp.back.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
