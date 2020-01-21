package pl.cpapp.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cpapp.back.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByUserId(Long userId);
}
