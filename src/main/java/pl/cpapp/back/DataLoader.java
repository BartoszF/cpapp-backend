package pl.cpapp.back;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.cpapp.back.model.Role;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.RoleRepository;
import pl.cpapp.back.service.UserService;

@Component
@Log
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(UserService userService, RoleRepository roleRepository) {

        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
        try {
            User admin = new User();
            admin.setNumber(System.getenv("adminNumber"));
            admin.setPin(System.getenv("adminPin"));
            admin.setPseudo("ADMIN");

            admin = userService.saveUser(admin);

            Role role = roleRepository.findByUserId(admin.getId());
            role.setRole("ADMIN");

            roleRepository.save(role);
        } catch (Exception ex) {
            log.info("Admin user already exists!");
            log.info(ex.getMessage());
        }
    }
}
