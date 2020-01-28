package pl.cpapp.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cpapp.back.data.request.AdminUserRequest;
import pl.cpapp.back.model.Role;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.RoleRepository;
import pl.cpapp.back.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User saveUser(User user) throws Exception {

        String pass = user.getPin();
        user.setPin(passwordEncoder.encode(pass));

        User createdUser = userRepository.save(user);

        Role role = new Role();
        role.setUser(createdUser);
        role.setRole("USER");

        roleRepository.save(role);

        return userRepository.getOne(user.getId());
    }

    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    public Optional<User> addContact(User currentUser, String contactNumber) {
        User contactUser = userRepository.findByNumber(contactNumber).orElseThrow(EntityNotFoundException::new);
        List<User> contacts = currentUser.getContacts();
        contacts.add(contactUser);
        currentUser.setContacts(contacts);

        return Optional.of(userRepository.save(currentUser));
    }

    public User createUser(AdminUserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setSurename(request.getSurename());
        user.setPseudo(request.getPseudo());
        user.setNumber(request.getPhoneNumber());
        user.setPin(passwordEncoder.encode(request.getPin()));
        user.setDescription(user.getDescription());

        return userRepository.save(user);
    }
}
